package com.formations.mappers;

import com.formations.model.Dto.TrainingDto;
import com.formations.model.Training;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface TrainingMapper {
    TrainingMapper trainingMapper = Mappers.getMapper(TrainingMapper.class);

    Training toEntity(TrainingDto trainingDto);
    TrainingDto toDto(Training training);


}
