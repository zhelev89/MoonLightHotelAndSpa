package team2.MoonLightHotelAndSpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import team2.MoonLightHotelAndSpa.model.contactUs.ContactUs;

@Repository
public interface ContactUsRepository extends JpaRepository<ContactUs, Long> {
}