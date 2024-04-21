package com.predev.gymcrm.repository;

import com.predev.gymcrm.entity.Account;
import com.predev.gymcrm.entity.Reservation;
import com.predev.gymcrm.entity.Trainer;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ReservationMapper {

    public int saveReservation(Reservation reservation);
    public List<Reservation> getAllReservation();
    public List<Reservation> findReservationsByUserId(int userId);
    public List<Reservation> findReservationByAccountId(int accountId);
    public List<Reservation> findReservationByDate(
            @Param("userId") int userId,
            @Param("trainerId") int trainerId,
            @Param("reservationDate") String reservationDate
    );
    List<Trainer> findTrainerByDay(
            @Param("reservationDate") String reservationDate,
            @Param("timeId") int timeId
    );
    List<Reservation> getTodayReservation(
            @Param("trainerId") int trainerId,
            @Param("today") String today
    );

    List<Reservation> findReservationByNameAndPeriod(
            @Param("searchType") int searchType,
            @Param("name") String name,
            @Param("startDate") String startDate,
            @Param("endDate") String endDate
    );

    List<Reservation> findReservationByAccountIdAndPeriod(
            @Param("accountId") int accountId,
            @Param("startDate") String startDate,
            @Param("endDate") String endDate
    );

    int findReservationCountByUserId(int userId);
    int findMemberCountOfTrainerByTrainerId(int trainerId);
    int deleteReservationByReservationId(int reservationId);
    int updateReservationByReservationId(
            @Param("prevReservationId")int prevReservationId,
            @Param("reservation") Reservation reservation
    );
}