package team2.MoonLightHotelAndSpa.runners;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import team2.MoonLightHotelAndSpa.models.reservations.RoomReservation;
import team2.MoonLightHotelAndSpa.models.rooms.Room;
import team2.MoonLightHotelAndSpa.models.rooms.RoomTitle;
import team2.MoonLightHotelAndSpa.models.rooms.RoomView;
import team2.MoonLightHotelAndSpa.models.users.Role;
import team2.MoonLightHotelAndSpa.services.RoleService;
import team2.MoonLightHotelAndSpa.services.RoomReservationService;
import team2.MoonLightHotelAndSpa.services.RoomService;
import team2.MoonLightHotelAndSpa.services.UserService;

import java.util.HashSet;

@Component
public class TestRunner implements CommandLineRunner {

    @Autowired
    private RoomService roomService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private UserService userService;

    @Autowired
    private RoomReservationService roomReservationService;

    @Override
    public void run(String... args) {
        //createdStandardRooms();
        //createdStudios();
        //createdApartments();
        //createdUserRoles();
        //createdRoomReservations();
    }
//
//    public void createdStandardRooms() {
//        Room room1 = new Room();
//        room1.setRoomTitle(RoomTitle.Standard);
//        room1.setArea(RoomView.Sea);
//        room1.setPrice(220.00F);
//        roomService.save(room1);
//
//        Room room2 = new Room();
//        room2.setRoomTitle(RoomTitle.Standard);
//        room2.setArea(RoomView.Sea);
//        room2.setPrice(220.00F);
//        roomService.save(room2);
//
//        Room room3 = new Room();
//        room3.setRoomTitle(RoomTitle.Standard);
//        room3.setArea(RoomView.Pool);
//        room3.setPrice(220.00F);
//        roomService.save(room3);
//
//        Room room4 = new Room();
//        room4.setRoomTitle(RoomTitle.Standard);
//        room4.setArea(RoomView.Pool);
//        room4.setPrice(220.00F);
//        roomService.save(room4);
//
//        Room room5 = new Room();
//        room5.setRoomTitle(RoomTitle.Standard);
//        room5.setArea(RoomView.Garden);
//        room5.setPrice(220.00F);
//        roomService.save(room5);
//
//        Room room6 = new Room();
//        room6.setRoomTitle(RoomTitle.Standard);
//        room6.setArea(RoomView.Garden);
//        room6.setPrice(220.00F);
//        roomService.save(room6);
//
//        Room room7 = new Room();
//        room7.setRoomTitle(RoomTitle.Standard);
//        room7.setArea(RoomView.Garden);
//        room7.setPrice(220.00F);
//        roomService.save(room7);
//
//        Room room8 = new Room();
//        room8.setRoomTitle(RoomTitle.Standard);
//        room8.setArea(RoomView.Garden);
//        room8.setPrice(220.00F);
//        roomService.save(room8);
//    }
//
//    public void createdStudios() {
//        Room room9 = new Room();
//        room9.setRoomTitle(RoomTitle.Studio);
//        room9.setArea(RoomView.Sea);
//        room9.setPrice(320.00F);
//        roomService.save(room9);
//
//        Room room10 = new Room();
//        room10.setRoomTitle(RoomTitle.Studio);
//        room10.setArea(RoomView.Sea);
//        room10.setPrice(320.00F);
//        roomService.save(room10);
//
//        Room room11 = new Room();
//        room11.setRoomTitle(RoomTitle.Studio);
//        room11.setArea(RoomView.Pool);
//        room11.setPrice(320.00F);
//        roomService.save(room11);
//
//        Room room12 = new Room();
//        room12.setRoomTitle(RoomTitle.Studio);
//        room12.setArea(RoomView.Pool);
//        room12.setPrice(320.00F);
//        roomService.save(room12);
//
//        Room room13 = new Room();
//        room13.setRoomTitle(RoomTitle.Studio);
//        room13.setArea(RoomView.Garden);
//        room13.setPrice(320.00F);
//        roomService.save(room13);
//
//        Room room14 = new Room();
//        room14.setRoomTitle(RoomTitle.Studio);
//        room14.setArea(RoomView.Garden);
//        room14.setPrice(320.00F);
//        roomService.save(room14);
//    }
//
//    public void createdApartments() {
//        Room room15 = new Room();
//        room15.setRoomTitle(RoomTitle.Apartment);
//        room15.setArea(RoomView.Pool);
//        room15.setPrice(520.00F);
//        roomService.save(room15);
//
//        Room room16 = new Room();
//        room16.setRoomTitle(RoomTitle.Apartment);
//        room16.setArea(RoomView.Sea);
//        room16.setPrice(520.00F);
//        roomService.save(room16);
//
//        Room room17 = new Room();
//        room17.setRoomTitle(RoomTitle.Apartment);
//        room17.setArea(RoomView.Sea);
//        room17.setPrice(520.00F);
//        roomService.save(room17);
//    }

    public void createdUserRoles() {
        Role role1 = new Role();
        role1.setRole("client");
        role1.setUsers(new HashSet<>());
        roleService.save(role1);

        Role role2 = new Role();
        role2.setRole("admin");
        role2.setUsers(new HashSet<>());
        roleService.save(role2);
    }

    public void createdRoomReservations() {
        RoomReservation roomReservation = new RoomReservation();
        roomReservationService.save(roomReservation);
    }
}

