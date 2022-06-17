package team2.MoonLightHotelAndSpa.runners;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import team2.MoonLightHotelAndSpa.models.reservations.RoomReservation;
import team2.MoonLightHotelAndSpa.models.rooms.Room;
import team2.MoonLightHotelAndSpa.models.rooms.RoomType;
import team2.MoonLightHotelAndSpa.models.rooms.RoomView;
import team2.MoonLightHotelAndSpa.models.users.Role;
import team2.MoonLightHotelAndSpa.models.users.User;
import team2.MoonLightHotelAndSpa.services.RoleService;
import team2.MoonLightHotelAndSpa.services.RoomReservationService;
import team2.MoonLightHotelAndSpa.services.RoomService;
import team2.MoonLightHotelAndSpa.services.UserService;

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
        createdUserRoles();
        //createdUsers();
        //createdRoomReservations();
    }

    public void createdStandardRooms() {
        Room room1 = new Room();
        room1.setRoomType(RoomType.Standard);
        room1.setRoomView(RoomView.Sea);
        room1.setRoomPrice(220.00);
        roomService.save(room1);

        Room room2 = new Room();
        room2.setRoomType(RoomType.Standard);
        room2.setRoomView(RoomView.Sea);
        room2.setRoomPrice(220.00);
        roomService.save(room2);

        Room room3 = new Room();
        room3.setRoomType(RoomType.Standard);
        room3.setRoomView(RoomView.Pool);
        room3.setRoomPrice(220.00);
        roomService.save(room3);

        Room room4 = new Room();
        room4.setRoomType(RoomType.Standard);
        room4.setRoomView(RoomView.Pool);
        room4.setRoomPrice(220.00);
        roomService.save(room4);

        Room room5 = new Room();
        room5.setRoomType(RoomType.Standard);
        room5.setRoomView(RoomView.Garden);
        room5.setRoomPrice(220.00);
        roomService.save(room5);

        Room room6 = new Room();
        room6.setRoomType(RoomType.Standard);
        room6.setRoomView(RoomView.Garden);
        room6.setRoomPrice(220.00);
        roomService.save(room6);

        Room room7 = new Room();
        room7.setRoomType(RoomType.Standard);
        room7.setRoomView(RoomView.Garden);
        room7.setRoomPrice(220.00);
        roomService.save(room7);

        Room room8 = new Room();
        room8.setRoomType(RoomType.Standard);
        room8.setRoomView(RoomView.Garden);
        room8.setRoomPrice(220.00);
        roomService.save(room8);
    }

    public void createdStudios() {
        Room room9 = new Room();
        room9.setRoomType(RoomType.Studio);
        room9.setRoomView(RoomView.Sea);
        room9.setRoomPrice(320.00);
        roomService.save(room9);

        Room room10 = new Room();
        room10.setRoomType(RoomType.Studio);
        room10.setRoomView(RoomView.Sea);
        room10.setRoomPrice(320.00);
        roomService.save(room10);

        Room room11 = new Room();
        room11.setRoomType(RoomType.Studio);
        room11.setRoomView(RoomView.Pool);
        room11.setRoomPrice(320.00);
        roomService.save(room11);

        Room room12 = new Room();
        room12.setRoomType(RoomType.Studio);
        room12.setRoomView(RoomView.Pool);
        room12.setRoomPrice(320.00);
        roomService.save(room12);

        Room room13 = new Room();
        room13.setRoomType(RoomType.Studio);
        room13.setRoomView(RoomView.Garden);
        room13.setRoomPrice(320.00);
        roomService.save(room13);

        Room room14 = new Room();
        room14.setRoomType(RoomType.Studio);
        room14.setRoomView(RoomView.Garden);
        room14.setRoomPrice(320.00);
        roomService.save(room14);
    }

    public void createdApartments() {
        Room room15 = new Room();
        room15.setRoomType(RoomType.Apartment);
        room15.setRoomView(RoomView.Pool);
        room15.setRoomPrice(520.00);
        roomService.save(room15);

        Room room16 = new Room();
        room16.setRoomType(RoomType.Apartment);
        room16.setRoomView(RoomView.Sea);
        room16.setRoomPrice(520.00);
        roomService.save(room16);

        Room room17 = new Room();
        room17.setRoomType(RoomType.Apartment);
        room17.setRoomView(RoomView.Sea);
        room17.setRoomPrice(520.00);
        roomService.save(room17);
    }

    public void createdUserRoles() {
        Role role1 = new Role();
        role1.setRole("Customer");
        roleService.save(role1);

        Role role2 = new Role();
        role2.setRole("Admin");
        roleService.save(role2);
    }

    public void createdUsers() {
        User user = new User();
        user.setFirstName("Zhivko");
        user.setLastName("Zhelev");
        user.setEmail("zhelev89@yahoo.com");
        user.setPassword("12345");
        user.setPhone("0899123123");
        userService.save(user);

        User user2 = new User();
        user2.setFirstName("Georgi");
        user2.setLastName("Ivanov");
        user2.setEmail("goshkata@yahoo.com");
        user2.setPassword("12345");
        user2.setPhone("0899123456");
        userService.save(user2);
    }

    public void createdRoomReservations() {
        RoomReservation roomReservation = new RoomReservation();
        roomReservationService.save(roomReservation);
    }
}

