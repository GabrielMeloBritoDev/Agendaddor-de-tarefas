package com.Agendador.tarefas.infrastructure.entity;

import com.Agendador.tarefas.infrastructure.Enuns.StatusNotificacaoEnum;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document("tarefa")
public class TarefaEntity {
    @Id
    private String id;
    private String nomeTarefa;
    private String descricao;
    private LocalDateTime dataCriacao;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime dataEvento;
    private String email;
    private LocalDateTime dataAlterecao;
    private StatusNotificacaoEnum statusNotificacaoEnum;
}
