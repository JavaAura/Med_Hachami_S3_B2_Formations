package com.formations.mappers;


import com.formations.model.Dto.LearnerDto;
import com.formations.model.Dto.TrainerDto;
import com.formations.model.Learner;
import com.formations.model.Trainer;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface TrainerMapper {
    TrainerMapper trainerMapper = Mappers.getMapper(TrainerMapper.class);

    Trainer toEntity(TrainerDto trainerDto);
    TrainerDto toDto(Trainer trainer);

}
