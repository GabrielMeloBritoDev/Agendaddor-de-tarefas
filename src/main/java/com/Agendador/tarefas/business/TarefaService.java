package com.Agendador.tarefas.business;

import com.Agendador.tarefas.business.Dto.TarefaDto;
import com.Agendador.tarefas.business.mapper.TarefaConverter;
import com.Agendador.tarefas.business.mapper.TarefaUpdateConverter;
import com.Agendador.tarefas.infrastructure.Enuns.StatusNotificacaoEnum;
import com.Agendador.tarefas.infrastructure.entity.TarefaEntity;
import com.Agendador.tarefas.infrastructure.exceptions.ResourceNotFoundException;
import com.Agendador.tarefas.infrastructure.repository.TarefasRepository;
import com.Agendador.tarefas.infrastructure.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TarefaService {

    private final TarefasRepository tarefasRepository;
    private final TarefaConverter tarefaConverter;
    private final JwtUtil jwtUtil;
    private final TarefaUpdateConverter tarefaUpdateConverter;


    public TarefaDto gravarTarefa(TarefaDto tarefaDto, String token) {

        String email = jwtUtil.extrairEmailDoToken(token.substring(7));
        tarefaDto.setDataCriacao(LocalDateTime.now());
        tarefaDto.setStatusNotificacaoEnum(StatusNotificacaoEnum.PEDENTE);
        tarefaDto.setEmail(email);
        TarefaEntity tarefasEntity = tarefaConverter.paraTarefaEntity(tarefaDto);

        return tarefaConverter.paraTarefaDto(tarefasRepository.save(tarefasEntity));
    }

    public List<TarefaDto> buscarTarefasAgendadasPorPeriodo(LocalDateTime dataInicial, LocalDateTime dataFinal) {
        return tarefaConverter.paraListaTarefasDto(
                tarefasRepository.findByDataEventoBetween(dataInicial, dataFinal));
    }

    public List<TarefaDto> buscaTarefaPorEmail(String token) {
        String email = jwtUtil.extrairEmailDoToken(token.substring(7));
        List<TarefaEntity> listasTarefas = tarefasRepository.findByemail(email);

        return tarefaConverter.paraListaTarefasDto(listasTarefas);
    }

    public void deletaTarefaPorId(String id) {
        try {
            tarefasRepository.deleteById(id);
        } catch (ResourceNotFoundException e) {
            throw new ResourceNotFoundException("Erro ao deletar por id, id inexistente " + id, e.getCause());
        }
    }

    public TarefaDto alteraStatus(StatusNotificacaoEnum statusNotificacaoEnum, String id) {
        try {
            TarefaEntity entity = tarefasRepository.findById(id).
                    orElseThrow(() -> new ResourceNotFoundException("Tarefa não encotrada " + id));
            entity.setStatusNotificacaoEnum(statusNotificacaoEnum);
            return tarefaConverter.paraTarefaDto(tarefasRepository.save(entity));
        } catch (ResourceNotFoundException e) {
            throw new ResourceNotFoundException("Erro ao alterar status da tarefa " + e.getCause());
        }
    }

    public TarefaDto updateTarefas(TarefaDto tarefaDto, String id) {
        try {
            TarefaEntity entity = tarefasRepository.findById(id).
                    orElseThrow(() -> new ResourceNotFoundException("Tarefa não encotrada " + id));
            tarefaUpdateConverter.updateTarefas(tarefaDto, entity);
            return tarefaConverter.paraTarefaDto(tarefasRepository.save(entity));
        } catch (ResourceNotFoundException e) {
            throw new ResourceNotFoundException("Erro ao deletar por id, id inexistente " + id, e.getCause());
        }
    }


}
