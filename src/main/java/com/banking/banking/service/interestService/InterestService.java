package com.banking.banking.service.interestService;

import java.util.List;

import javax.transaction.Transactional;

import com.banking.banking.model.interest.Interest;
import com.banking.banking.repository.interestRepo.InterestRepository;

import org.springframework.stereotype.Service;

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
