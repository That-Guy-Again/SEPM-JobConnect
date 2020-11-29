package at.ac.tuwien.sepm.groupphase.backend.service.impl;

import at.ac.tuwien.sepm.groupphase.backend.entity.Employer;
import at.ac.tuwien.sepm.groupphase.backend.entity.Profile;
import at.ac.tuwien.sepm.groupphase.backend.exception.NotFoundException;
import at.ac.tuwien.sepm.groupphase.backend.exception.UniqueConstraintException;
import at.ac.tuwien.sepm.groupphase.backend.repository.EmployerRepository;
import at.ac.tuwien.sepm.groupphase.backend.repository.ProfileRepository;
import at.ac.tuwien.sepm.groupphase.backend.service.EmployerService;
import at.ac.tuwien.sepm.groupphase.backend.service.ProfileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.lang.invoke.MethodHandles;

@Service
public class EmployerServiceImpl implements EmployerService {
    private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    private final EmployerRepository employerRepository;
    private final PasswordEncoder passwordEncoder;
    private final ProfileService profileService;
    private final ProfileRepository profileRepository;

    @Autowired
    public EmployerServiceImpl(EmployerRepository employerRepository, PasswordEncoder passwordEncoder, ProfileService profileService, ProfileRepository profileRepository) {
        this.employerRepository = employerRepository;
        this.passwordEncoder = passwordEncoder;
        this.profileService = profileService;
        this.profileRepository = profileRepository;
    }

    @Override
    public Long createEmployer(Employer employer) throws UniqueConstraintException {
        employer.getProfile().setPassword(passwordEncoder.encode(employer.getProfile().getPassword()));
        employer.setId(profileService.createProfile(employer.getProfile()));
        return employerRepository.save(employer).getId();
    }

    @Override
    public Employer findOneByEmail(String email) {
        LOGGER.info("Find employer with email {}", email);
        Employer employer = employerRepository.findByProfile_Email(email);
        if (employer != null) return employer;
        else throw new NotFoundException(String.format("Could not find employer with email %s", email));
    }

    @Override
    public Long updateEmployer(Employer employer) {
        LOGGER.info("Update employer: {}", employer);

        Profile profile = profileService.findProfileByEmail(employer.getProfile().getEmail());
        if(!employer.getProfile().getPassword().isBlank())
            employer.getProfile().setPassword(passwordEncoder.encode(employer.getProfile().getPassword()));
        else
            employer.getProfile().setPassword(profile.getPassword());

        employer.getProfile().setId(profile.getId());
        employer.setId(profile.getId());

        profileRepository.save(employer.getProfile());
        return employerRepository.save(employer).getId();
    }
}
