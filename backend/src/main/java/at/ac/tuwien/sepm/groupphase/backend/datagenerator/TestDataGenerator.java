package at.ac.tuwien.sepm.groupphase.backend.datagenerator;

import at.ac.tuwien.sepm.groupphase.backend.entity.*;
import at.ac.tuwien.sepm.groupphase.backend.repository.*;
import at.ac.tuwien.sepm.groupphase.backend.util.Gender;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.lang.invoke.MethodHandles;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Profile("generateData")
@Component
public class TestDataGenerator {

    private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    private final String[] companyNames = {
        "Alladins Lamp",
        "Architectural Genie",
        "Asian Answers",
        "Avant Garde Interior Designs",
        "Back To Basics Chiropractic Clinic",
        "Balanced Fortune",
        "Beasts of Beauty",
        "Belle Ladi",
        "Belle Lady",
        "Benesome",
        "Best Biz Survis",
        "Better Business Ideas and Services",
        "Buena Vista Garden Maintenance",
        "First Choice Yard Help",
        "First Rate Choice",
        "Fit Tonic",
        "Flexus",
        "Formula Grey",
        "Four Leaf Clover",
        "Fragrant Flower Lawn Services",
        "Freedom Map",
        "Fresh Start",
        "Friendly Advice",
        "Full Color",
        "Future Bright",
        "Future Plan",
        "Galaxy Man",
        "Gamma Gas",
        "Gamma Realty",
        "Garden Guru",
        "Listen Up",
        "Locost Accessories",
        "Lone Wolf Wealth Planning",
        "Magna Consulting",
        "Matrix Architectural Service",
        "Magik Lamp",
        "Magna Wealth",
        "ManPower",
        "Manu Connection",
        "Maxaprofit"
    };

    String[] names = {
        "Lucy Bauer",
        "Lukas Müller",
        "Ella Schwartz",
        "Konstantin Hofer",
        "Amy Gruber",
        "Ben Huber",
        "Emely Egger",
        "Jonas Wagner",
        "Finja Schmidt",
        "Elias Weiss",
        "Amelie Wolf",
        "Niklas Haas",
        "Luise Fuchs",
        "David Edner",
        "Frieda Schmid",
        "Oskar Maier",
        "Katharina Weber",
        "Philipp Reiter",
        "Romy Wimmer",
        "Leon Baumgartner",
        "Juna Lackner",
        "Noah Stadler",
        "Theresa Berger",
        "Luis Pichler",
        "Eva Moser",
        "Paul Aigner",
        "Julia Koller",
        "Finn Graf",
        "Anna Lechner",
        "Felix Maier"
    };

    // Employer
    private static final String TEST_EMPLOYER_FIRST_NAME = "FIRST NAME";
    private static final String TEST_EMPLOYER_LAST_NAME = "LAST NAME";
    private static final String TEST_PUBLIC_INFO = "PUBLIC INFO";
    private static final String TEST_PASSWORD = "123456789";
    private static final LocalDateTime TEST_BIRTHDATE
        = LocalDateTime.of(2000, 1, 1, 0, 0, 0, 0);

    // Event
    private static final String TEST_EVENT_TITLE1 = "Weihnachtsfeier";
    private static final String TEST_EVENT_TITLE2 = "Putzhilfe";
    private static final String TEST_EVENT_TITLE3 = "BabysitterIn gesucht für 3 jähriges Mädchen";
    private static final String TEST_EVENT_TITLE4 = "Computerunterricht";
    private static final String TEST_EVENT_DESCRIPTION1 = "Unsere Firma plant für den 24.12 Nachmittag eine kleine Feier und wir benötigen KellnerInnen und eine(n) DJ.";
    private static final String TEST_EVENT_DESCRIPTION2 = "Reinigungsarbeit für das gesamte Haus (Küche, Wohnzimmer, Badezimmer, Schlafzimmer), zirka 60m²";
    private static final String TEST_EVENT_DESCRIPTION3 = "Sind auf der Suche nach einem netten Menschen der gerne und gut mit unserer Kleinen umgehen und aufpassen kann!";
    private static final String TEST_EVENT_DESCRIPTION4 = "Unsere Oma hat einen neuen Laptop bekommen und braucht dringend Einstiegshilfe!";

    private static final LocalDateTime TEST_EVENT_START1
        = LocalDateTime.of(2020, 12, 24, 12, 0, 0, 0);
    private static final LocalDateTime TEST_EVENT_END1
        = LocalDateTime.of(2020, 12, 24, 18, 0, 0, 0);

    private static final LocalDateTime TEST_EVENT_START2
        = LocalDateTime.of(2020, 12, 20, 13, 0, 0, 0);
    private static final LocalDateTime TEST_EVENT_END2
        = LocalDateTime.of(2020, 12, 20, 18, 0, 0, 0);

    private static final LocalDateTime TEST_EVENT_START3
        = LocalDateTime.of(2020, 12, 25, 8, 0, 0, 0);
    private static final LocalDateTime TEST_EVENT_END3
        = LocalDateTime.of(2021, 1, 25, 20, 0, 0, 0);

    private static final LocalDateTime TEST_EVENT_START4
        = LocalDateTime.of(2021, 3, 13, 7, 15, 0, 0);
    private static final LocalDateTime TEST_EVENT_END4
        = LocalDateTime.of(2021, 3, 13, 16, 15, 0, 0);

    // Addresses
    private static final Address TEST_ADDRESS1 = Address.AddressBuilder.aAddress()
        .withAddressLine("Mariahilfer Straße 5")
        .withCity("Wien")
        .withState("Wien")
        .withZip(1070)
        .build();
    private static final Address TEST_ADDRESS2 = Address.AddressBuilder.aAddress()
        .withAddressLine("Morzingasse 22")
        .withCity("Mariazell")
        .withState("Steiermark")
        .withZip(8100)
        .build();
    private static final Address TEST_ADDRESS3 = Address.AddressBuilder.aAddress()
        .withAddressLine("Kapfweg 12")
        .withCity("Feldkirch")
        .withState("Vorarlberg")
        .withZip(6800)
        .build();
    private static final Address TEST_ADDRESS4 = Address.AddressBuilder.aAddress()
        .withAddressLine("Heimstraße 4")
        .withCity("Villach")
        .withState("Kärnten")
        .withZip(9500)
        .build();

    // Tasks
    private static final Task TEST_TASK1 = Task.TaskBuilder.aTask()
        .withDescription("KellnerIn für Getränke und Snacks servieren, Tische abräumen und sauber machen.")
        .withEmployeeCount(3)
        .withPaymentHourly(9.0)
        .build();
    private static final Task TEST_TASK1_1 = Task.TaskBuilder.aTask()
        .withDescription("jemand mit DJ Erfahrung der für gute Stimmung sorgen kann")
        .withEmployeeCount(1)
        .withPaymentHourly(10.0)
        .build();
    private static final Task TEST_TASK2 = Task.TaskBuilder.aTask()
        .withDescription("Putzhilfe")
        .withEmployeeCount(1)
        .withPaymentHourly(10.0)
        .build();
    private static final Task TEST_TASK3 = Task.TaskBuilder.aTask()
        .withDescription("BabysitteIn")
        .withEmployeeCount(1)
        .withPaymentHourly(8.0)
        .build();
    private static final Task TEST_TASK4 = Task.TaskBuilder.aTask()
        .withDescription("jemand der sich gut mit Computern und IT auskennt und älteren Damen diesen Bereich gut erklären und beibringen kann")
        .withEmployeeCount(1)
        .withPaymentHourly(16.0)
        .build();

    private final EmployeeRepository employeeRepository;
    private final EmployerRepository employerRepository;
    private final ProfileRepository profileRepository;
    private final EventRepository eventRepository;
    private final AddressRepository addressRepository;
    private final TaskRepository taskRepository;
    private final PasswordEncoder passwordEncoder;
    private final InterestAreaRepository interestAreaRepository;
    private final InterestRepository interestRepository;
    private final TimeRepository timeRepository;


    public TestDataGenerator(EmployeeRepository employeeRepository, EmployerRepository employerRepository,
                             ProfileRepository profileRepository, PasswordEncoder passwordEncoder,
                             EventRepository eventRepository, AddressRepository addressRepository,
                             TaskRepository taskRepository, InterestAreaRepository interestAreaRepository,
                             InterestRepository interestRepository, TimeRepository timeRepository) {
        this.employeeRepository = employeeRepository;
        this.employerRepository = employerRepository;
        this.profileRepository = profileRepository;
        this.eventRepository = eventRepository;
        this.addressRepository = addressRepository;
        this.taskRepository = taskRepository;
        this.interestAreaRepository = interestAreaRepository;
        this.passwordEncoder = passwordEncoder;
        this.interestRepository = interestRepository;
        this.timeRepository = timeRepository;
    }

    public void generateEmployers() {
        if (employerRepository.findAll().size() > 0) {
            LOGGER.debug("employers already generated");
        } else {
            LOGGER.debug("generating {} employer entries", companyNames.length);
            for (String name : companyNames) {
                at.ac.tuwien.sepm.groupphase.backend.entity.Profile employerProfile =
                    at.ac.tuwien.sepm.groupphase.backend.entity.Profile.ProfileBuilder.aProfile()
                        .withEmail("test@" + name.replace(" ", "").toLowerCase() + ".at")
                        .withForename(TEST_EMPLOYER_FIRST_NAME)
                        .withName(TEST_EMPLOYER_LAST_NAME)
                        .withPassword(passwordEncoder.encode(TEST_PASSWORD))
                        .withPublicInfo(TEST_PUBLIC_INFO)
                        .isEmployer(true)
                        .build();

                Employer employer = Employer.EmployerBuilder.aEmployer()
                    .withCompanyName(name)
                    .withDescription("generated employer")
                    .withProfile(employerProfile)
                    .build();
                LOGGER.debug("saving employer {}", employer);
                Long id = profileRepository.save(employerProfile).getId();
                employer.setId(id);
                employerRepository.save(employer);
            }
        }
    }

    private void generateEmployees() {
        if (employeeRepository.findAll().size() > 0) {
            LOGGER.debug("employees already generated");
        } else {
            LOGGER.debug("generating {} employee entries", names.length);
            int count = 0;
            for (String name : names) {
                at.ac.tuwien.sepm.groupphase.backend.entity.Profile employeeProfile =
                    at.ac.tuwien.sepm.groupphase.backend.entity.Profile.ProfileBuilder.aProfile()
                        .withEmail(name.replace(" ", ".").toLowerCase() + "@gmail.com")
                        .withForename(name.split(" ")[0])
                        .withName(name.split(" ")[1])
                        .withPassword(passwordEncoder.encode(TEST_PASSWORD))
                        .withPublicInfo(TEST_PUBLIC_INFO)
                        .isEmployer(false)
                        .build();

                Employee employee = Employee.EmployeeBuilder.aEmployee()
                    .withGender(count % 2 == 0 ? Gender.FEMALE : Gender.MALE)
                    .withBirthDate(TEST_BIRTHDATE)
                    .withProfile(employeeProfile)
                    .build();
                LOGGER.debug("saving employee {}", employee);
                Long id = profileRepository.save(employeeProfile).getId();
                employee.setId(id);
                employeeRepository.save(employee);
                count++;
            }
        }
    }

    private void generateInterestAreas() {
        if (interestAreaRepository.findAll().size() > 0) {
            LOGGER.debug("interestAreas already generated");
        } else {
            LOGGER.debug("generating {} interestAreas entries", 16);
            InterestArea interestArea1 = InterestArea.InterestAreaBuilder.aInterest()
                .withArea("Service")
                .withDescription("Promo (z.B. Flyer verteilen)")
                .build();
            InterestArea interestArea1_1 = InterestArea.InterestAreaBuilder.aInterest()
                .withArea("Service")
                .withDescription("Verkaufshilfe")
                .build();
            InterestArea interestArea1_2 = InterestArea.InterestAreaBuilder.aInterest()
                .withArea("Service")
                .withDescription("Messebetreuung")
                .build();
            InterestArea interestArea1_3 = InterestArea.InterestAreaBuilder.aInterest()
                .withArea("Service")
                .withDescription("KellnerIn")
                .build();
            InterestArea interestArea2 = InterestArea.InterestAreaBuilder.aInterest()
                .withArea("Haushalt")
                .withDescription("Babysitter")
                .build();
            InterestArea interestArea2_1 = InterestArea.InterestAreaBuilder.aInterest()
                .withArea("Haushalt")
                .withDescription("Tiersitter")
                .build();
            InterestArea interestArea2_2 = InterestArea.InterestAreaBuilder.aInterest()
                .withArea("IT")
                .withDescription("Computerhilfe")
                .build();
            InterestArea interestArea2_3 = InterestArea.InterestAreaBuilder.aInterest()
                .withArea("Haushalt")
                .withDescription("Gartenhilfe")
                .build();
            InterestArea interestArea2_4 = InterestArea.InterestAreaBuilder.aInterest()
                .withArea("Haushalt")
                .withDescription("Putzhilfe")
                .build();
            InterestArea interestArea3 = InterestArea.InterestAreaBuilder.aInterest()
                .withArea("Handwerk")
                .withDescription("Umzugshelfer (vor allem schwere Lasten tragen)")
                .build();
            InterestArea interestArea3_1 = InterestArea.InterestAreaBuilder.aInterest()
                .withArea("Handwerk")
                .withDescription("Autofahrer")
                .build();
            InterestArea interestArea3_2 = InterestArea.InterestAreaBuilder.aInterest()
                .withArea("Handwerk")
                .withDescription("Aufbauhilfe (z.B. Bühnen)")
                .build();
            InterestArea interestArea3_3 = InterestArea.InterestAreaBuilder.aInterest()
                .withArea("Handwerk")
                .withDescription("Aufbauhilfe (z.B. Möbel)")
                .build();
            InterestArea interestArea4 = InterestArea.InterestAreaBuilder.aInterest()
                .withArea("Musik")
                .withDescription("DJ")
                .build();
            InterestArea interestArea4_1 = InterestArea.InterestAreaBuilder.aInterest()
                .withArea("IT")
                .withDescription("Backend Dev")
                .build();
            InterestArea interestArea4_2 = InterestArea.InterestAreaBuilder.aInterest()
                .withArea("IT")
                .withDescription("Frontend Dev")
                .build();

            LOGGER.debug("saving interestArea {}", interestArea1);
            interestAreaRepository.save(interestArea1);
            LOGGER.debug("saving interestArea {}", interestArea1_1);
            interestAreaRepository.save(interestArea1_1);
            LOGGER.debug("saving interestArea {}", interestArea1_2);
            interestAreaRepository.save(interestArea1_2);
            LOGGER.debug("saving interestArea {}", interestArea1_3);
            interestAreaRepository.save(interestArea1_3);

            LOGGER.debug("saving interestArea {}", interestArea2);
            interestAreaRepository.save(interestArea2);
            LOGGER.debug("saving interestArea {}", interestArea2_1);
            interestAreaRepository.save(interestArea2_1);
            LOGGER.debug("saving interestArea {}", interestArea2_2);
            interestAreaRepository.save(interestArea2_2);
            LOGGER.debug("saving interestArea {}", interestArea2_3);
            interestAreaRepository.save(interestArea2_3);
            LOGGER.debug("saving interestArea {}", interestArea2_4);
            interestAreaRepository.save(interestArea2_4);

            LOGGER.debug("saving interestArea {}", interestArea3);
            interestAreaRepository.save(interestArea3);
            LOGGER.debug("saving interestArea {}", interestArea3_1);
            interestAreaRepository.save(interestArea3_1);
            LOGGER.debug("saving interestArea {}", interestArea3_2);
            interestAreaRepository.save(interestArea3_2);
            LOGGER.debug("saving interestArea {}", interestArea3_3);
            interestAreaRepository.save(interestArea3_3);

            LOGGER.debug("saving interestArea {}", interestArea4);
            interestAreaRepository.save(interestArea4);
            LOGGER.debug("saving interestArea {}", interestArea4_1);
            interestAreaRepository.save(interestArea4_1);
            LOGGER.debug("saving interestArea {}", interestArea4_2);
            interestAreaRepository.save(interestArea4_2);

        }
    }

    private void generateInterests() {
        if (interestRepository.findAll().size() > 0) {
            LOGGER.debug("interests already generated");
        } else {
            LOGGER.debug("generating {} interests entries", 5);

            for (Employee employee :
                employeeRepository.findAll()) {

                Interest interest1 = Interest.InterestBuilder.aInterest()
                    .withName("IT")
                    .withDescription("ich programmiere gerne")
                    .withInterestArea(interestAreaRepository.getOne(15L))
                    .withEmployee(employee)
                    .build();
                Interest interest2 = Interest.InterestBuilder.aInterest()
                    .withName("Babysitter")
                    .withDescription("passe gerne auf kleine Kinder und Babys auf")
                    .withInterestArea(interestAreaRepository.getOne(5L))
                    .withEmployee(employee)
                    .build();
                Interest interest3 = Interest.InterestBuilder.aInterest()
                    .withName("spazieren gehen")
                    .withDescription("in meiner Freizeit verbringe ich gerne Zeit im Freien")
                    .withInterestArea(interestAreaRepository.getOne(6L))
                    .withEmployee(employee)
                    .build();
                Interest interest4 = Interest.InterestBuilder.aInterest()
                    .withName("Musik produzieren")
                    .withDescription("ab und zu versuche ich mich als Hobby-DJ")
                    .withInterestArea(interestAreaRepository.getOne(14L))
                    .withEmployee(employee)
                    .build();
                Interest interest5 = Interest.InterestBuilder.aInterest()
                    .withName("Bücher lesen")
                    .withDescription("ich lese gerne")
                    .withInterestArea(null)
                    .withEmployee(employee)
                    .build();

                LOGGER.debug("saving interest {}", interest1);
                interestRepository.save(interest1);
                LOGGER.debug("saving interest {}", interest2);
                interestRepository.save(interest2);
                LOGGER.debug("saving interest {}", interest3);
                interestRepository.save(interest3);
                LOGGER.debug("saving interest {}", interest4);
                interestRepository.save(interest4);
                LOGGER.debug("saving interest {}", interest5);
                interestRepository.save(interest5);
            }

        }
    }

    private void generateTimes() {
        if (timeRepository.findAll().size() > 0) {
            LOGGER.debug("times already generated");
        } else {
            LOGGER.debug("generating {} time entries", 3);

            for (Employee employee :
                employeeRepository.findAll()) {

                Time time1 = Time.TimeBuilder.aTime()
                    .withStart(LocalDateTime.of(2020, 12, 24, 12, 0, 0, 0))
                    .withEnd(LocalDateTime.of(2020, 12, 24, 18, 0, 0, 0))
                    .withFinalEndDate(LocalDateTime.of(2020, 12, 24, 18, 0, 0, 0))
                    .withVisible(true)
                    .withRef_Id(-1L)
                    .withEmployee(employee)
                    .build();

                Time time2 = Time.TimeBuilder.aTime()
                    .withStart(LocalDateTime.of(2021, 1, 7, 17, 0, 0, 0))
                    .withEnd(LocalDateTime.of(2021, 1, 7, 23, 0, 0, 0))
                    .withFinalEndDate(LocalDateTime.of(2021, 1, 7, 23, 0, 0, 0))
                    .withVisible(true)
                    .withRef_Id(-1L)
                    .withEmployee(employee)
                    .build();

                LOGGER.debug("saving time {}", time1);
                timeRepository.save(time1);
                LOGGER.debug("saving time {}", time2);
                timeRepository.save(time2);
            }
        }
    }

    @PostConstruct
    public void generateEvents() {
        generateEmployers();
        generateEmployees();
        generateInterestAreas();
        generateInterests();
        generateTimes();
        if (eventRepository.findAll().size() > 0) {
            LOGGER.debug("events already generated");
        } else {

            TEST_TASK1.setInterestArea(interestAreaRepository.getOne(4L));
            TEST_TASK1_1.setInterestArea(interestAreaRepository.getOne(14L));

            TEST_TASK2.setInterestArea(interestAreaRepository.getOne(9L));

            TEST_TASK3.setInterestArea(interestAreaRepository.getOne(5L));

            TEST_TASK4.setInterestArea(interestAreaRepository.getOne(7L));


            Set<Task> tasks1 = new HashSet<>();
            tasks1.add(TEST_TASK1);
            tasks1.add(TEST_TASK1_1);

            Set<Task> tasks2 = new HashSet<>();
            tasks2.add(TEST_TASK2);

            Set<Task> tasks3 = new HashSet<>();
            tasks3.add(TEST_TASK3);

            Set<Task> tasks4 = new HashSet<>();
            tasks4.add(TEST_TASK4);

            Event event1 = Event.EventBuilder.aEvent()
                .withTitle(TEST_EVENT_TITLE1)
                .withDescription(TEST_EVENT_DESCRIPTION1)
                .withStart(TEST_EVENT_START1)
                .withEnd(TEST_EVENT_END1)
                .withAddress(addressRepository.save(TEST_ADDRESS1))
                .withTask(tasks1)
                .withEmployer(employerRepository.findByProfile_Email("test@freedommap.at"))
                .build();
            Event event2 = Event.EventBuilder.aEvent()
                .withTitle(TEST_EVENT_TITLE2)
                .withDescription(TEST_EVENT_DESCRIPTION2)
                .withStart(TEST_EVENT_START2)
                .withEnd(TEST_EVENT_END2)
                .withAddress(addressRepository.save(TEST_ADDRESS2))
                .withTask(tasks2)
                .withEmployer(employerRepository.findByProfile_Email("test@galaxyman.at"))
                .build();
            Event event3 = Event.EventBuilder.aEvent()
                .withTitle(TEST_EVENT_TITLE3)
                .withDescription(TEST_EVENT_DESCRIPTION3)
                .withStart(TEST_EVENT_START3)
                .withEnd(TEST_EVENT_END3)
                .withAddress(addressRepository.save(TEST_ADDRESS3))
                .withTask(tasks3)
                .withEmployer(employerRepository.findByProfile_Email("test@futureplan.at"))
                .build();
            Event event4 = Event.EventBuilder.aEvent()
                .withTitle(TEST_EVENT_TITLE4)
                .withDescription(TEST_EVENT_DESCRIPTION4)
                .withStart(TEST_EVENT_START4)
                .withEnd(TEST_EVENT_END4)
                .withAddress(addressRepository.save(TEST_ADDRESS4))
                .withTask(tasks4)
                .withEmployer(employerRepository.findByProfile_Email("test@maxaprofit.at"))
                .build();

            TEST_TASK1.setEvent(event1);
            TEST_TASK1_1.setEvent(event1);
            TEST_TASK2.setEvent(event2);
            TEST_TASK3.setEvent(event3);
            TEST_TASK4.setEvent(event4);

            LOGGER.debug("saving event {}", event1);
            eventRepository.save(event1);
            LOGGER.debug("saving event {}", event2);
            eventRepository.save(event2);
            LOGGER.debug("saving event {}", event3);
            eventRepository.save(event3);
            LOGGER.debug("saving event {}", event4);
            eventRepository.save(event4);

            LOGGER.debug("saving task {}", TEST_TASK1);
            taskRepository.save(TEST_TASK1);
            LOGGER.debug("saving task {}", TEST_TASK1_1);
            taskRepository.save(TEST_TASK1_1);
            LOGGER.debug("saving task {}", TEST_TASK2);
            taskRepository.save(TEST_TASK2);
            LOGGER.debug("saving task {}", TEST_TASK3);
            taskRepository.save(TEST_TASK3);
            LOGGER.debug("saving task {}", TEST_TASK4);
            taskRepository.save(TEST_TASK4);
        }
    }

}
