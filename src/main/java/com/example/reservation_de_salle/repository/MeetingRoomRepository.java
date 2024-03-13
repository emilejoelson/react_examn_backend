package com.example.reservation_de_salle.repository;

import com.example.reservation_de_salle.entities.MeetingRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface MeetingRoomRepository extends JpaRepository<MeetingRoom,Long> {
}
