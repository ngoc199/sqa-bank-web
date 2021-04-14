package com.banking.banking.service.interestService;

import java.util.List;

import javax.transaction.Transactional;

import com.banking.banking.model.interest.Interest;
import com.banking.banking.repository.interestRepo.InterestRepository;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class InterestService {
    private final InterestRepository interestRepo;

    /**
     * Get all interests' information in the database
     *
     * @return interestList
     */
    public List<Interest> getAllInterests() {
        return interestRepo.findAll();
    }

    /**
     * Get the interest by id
     *
     * @param interestId
     * @return interest
     * @exception ResponseStatusException
     */
    public Interest getInterestById(int interestId) {
        return interestRepo.findById(interestId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    /**
     * Update the interest's information
     *
     * @param updatedInterest
     * @return updateResult
     */
    @Transactional(rollbackOn = Exception.class)
    public boolean updateInterest(Interest updatedInterest) {

        // If the updated interest is not existed in the database, then abort updating
        // interest
        if (!interestRepo.findById(updatedInterest.getId()).isPresent()) {
            return false;
        }

        // Update the interest
        interestRepo.save(updatedInterest);
        return true;
    }

    /**
     * Add new interest
     *
     * @param interest
     * @return addResult
     */
    @Transactional(rollbackOn = Exception.class)
    public boolean addInterest(Interest interest) {
        return interestRepo.save(interest) != null;
    }

    /**
     * Delete interest by id
     *
     * @param interestId
     */
    @Transactional(rollbackOn = Exception.class)
    public void deleteInterest(int interestId) {
        interestRepo.deleteById(interestId);
    }
}
