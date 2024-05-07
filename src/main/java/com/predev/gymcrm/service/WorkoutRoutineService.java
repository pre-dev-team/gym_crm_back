package com.predev.gymcrm.service;

import com.predev.gymcrm.dto.req.TrainerAddRoutineReqDto;
import com.predev.gymcrm.dto.resp.TrainerSearchWorkoutRoutineRespDto;
import com.predev.gymcrm.entity.WorkoutRoutine;
import com.predev.gymcrm.repository.WorkoutRoutineMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class WorkoutRoutineService {

    @Autowired
    WorkoutRoutineMapper workoutRoutineMapper;

    public int insertRoutine(List<TrainerAddRoutineReqDto> trainerAddRoutineReqDtos) {
        int successCount = 0;
        List<WorkoutRoutine> prevWorkoutRoutines = workoutRoutineMapper.findWorkoutRoutines(trainerAddRoutineReqDtos.get(0).getReservationId());
        if(prevWorkoutRoutines.isEmpty()) {
            List<WorkoutRoutine> workoutRoutines = trainerAddRoutineReqDtos.stream().map(TrainerAddRoutineReqDto::toEntity).collect(Collectors.toList());
            successCount = workoutRoutineMapper.saveRoutines(workoutRoutines);
        }
        return successCount;
    }

    public List<TrainerSearchWorkoutRoutineRespDto> searchRoutinesByReservationId(int reservationId) {
        List<WorkoutRoutine> workoutRoutines = workoutRoutineMapper.findWorkoutRoutines(reservationId);
        return workoutRoutines.stream().map(WorkoutRoutine::toSearchWorkoutRoutineRespDto).collect(Collectors.toList());
    }

    @Transactional(rollbackFor = Exception.class)
    public int editRoutines(List<TrainerAddRoutineReqDto> trainerAddRoutineReqDtos) {
        int successCount = 0;
        successCount += workoutRoutineMapper.deleteRoutines(trainerAddRoutineReqDtos.get(0).getReservationId());
        List<WorkoutRoutine> workoutRoutines = trainerAddRoutineReqDtos.stream().map(TrainerAddRoutineReqDto::toEntity).collect(Collectors.toList());
        successCount += workoutRoutineMapper.saveRoutines(workoutRoutines);
        return successCount;
    }

}
