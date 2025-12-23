package com.example.demo.service.impl;

import com.example.demo.model.KeyShareRequest;
import com.example.demo.repository.KeyShareRequestRepository;
import com.example.demo.service.KeyShareRequestService;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
public class KeyShareRequestServiceImpl implements KeyShareRequestService {

    private final KeyShareRequestRepository keyShareRequestRepository;

    public KeyShareRequestServiceImpl(KeyShareRequestRepository keyShareRequestRepository) {
        this.keyShareRequestRepository = keyShareRequestRepository;
    }

    @Override
    public KeyShareRequest createShareRequest(KeyShareRequest req) {

        Instant start = req.getShareStart();
        Instant end = req.getShareEnd();

        if (end.isBefore(start)) {
            throw new IllegalArgumentException("Share end before start");
        }

        if (req.getSharedBy().getId().equals(req.getSharedWith().getId())) {
            throw new IllegalArgumentException("sharedBy and sharedWith cannot be same");
        }

        return keyShareRequestRepository.save(req);
    }

    @Override
    public List<KeyShareRequest> getRequestsSharedBy(Long guestId) {
        return keyShareRequestRepository.findBySharedById(guestId);
    }

    @Override
    public List<KeyShareRequest> getRequestsSharedWith(Long guestId) {
        return keyShareRequestRepository.findBySharedWithId(guestId);
    }
}
