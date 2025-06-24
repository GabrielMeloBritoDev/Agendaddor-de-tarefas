package com.Agendador.tarefas.business.mapper;

import com.Agendador.tarefas.business.Dto.TarefaDto;
import com.Agendador.tarefas.infrastructure.entity.TarefaEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValueMappingStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy  = NullValuePropertyMappingStrategy.IGNORE)
public interface TarefaUpdateConverter {

    void updateTarefas(TarefaDto tarefaDto,@MappingTarget TarefaEntity tarefaEntity);
}
