package com.taulia.example.tdo

import org.springframework.data.jpa.repository.JpaRepository

interface WidgetRepository extends JpaRepository<WidgetTdo, String> {
}
