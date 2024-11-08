package com.formations.mappers;

import com.formations.model.Dto.LearnerDto;
import com.formations.model.Learner;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;


@Mapper
public interface LearnerMapper {
    LearnerMapper learnerMapper = Mappers.getMapper(LearnerMapper.class);

    Learner toEntity(LearnerDto learnerDto);
    LearnerDto toDto(Learner learner);
}
