package com.Agendador.tarefas.controller;

import com.Agendador.tarefas.business.Dto.TarefaDto;
import com.Agendador.tarefas.business.TarefaService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/tarefa")
@RequiredArgsConstructor
public class TarefaController {

    private final TarefaService tarefaService;

    @PostMapping
    public ResponseEntity<TarefaDto> gravarTarefa(@RequestBody TarefaDto tarefaDto,
                                                  @RequestHeader("Authorization") String token){
        return ResponseEntity.ok(tarefaService.gravarTarefa(tarefaDto,token));
    }

    @GetMapping("/eventos")
    public ResponseEntity<List<TarefaDto>> buscarTarefasAgendadasPorPeriodo(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataInicial,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataFinal) {

        return ResponseEntity.ok(
                tarefaService.buscarTarefasAgendadasPorPeriodo(dataInicial, dataFinal)
        );
    }


    @GetMapping
    public ResponseEntity<List<TarefaDto>> buscarTarefaPorEmail(@RequestHeader("Authorization") String token ){
        return ResponseEntity.ok(tarefaService.buscaTarefaPorEmail(token));
    }
}
