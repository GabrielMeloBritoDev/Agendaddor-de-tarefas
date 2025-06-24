package com.Agendador.tarefas.business.mapper;

import com.Agendador.tarefas.business.Dto.TarefaDto;
import com.Agendador.tarefas.infrastructure.entity.TarefaEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TarefaConverter {

    TarefaEntity paraTarefaEntity(TarefaDto tarefaDto);
    TarefaDto paraTarefaDto(TarefaEntity tarefasEntity);
    List<TarefaEntity> paraListaTarefasEntity(List<TarefaDto> tarefaDtos);
    List<TarefaDto> paraListaTarefasDto(List<TarefaEntity> tarefaEntities);
}
