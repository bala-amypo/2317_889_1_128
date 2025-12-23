package com.example.demo.service.impl;

import java.time.Instant;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.AccessLog;
import com.example.demo.model.DigitalKey;
import com.example.demo.repository.AccessLogRepository;
import com.example.demo.repository.DigitalKeyRepository;
import com.example.demo.repository.GuestRepository;
import com.example.demo.repository.KeyShareRequestRepository;
import com.example.demo.service.AccessLogService;

@Service
public class AccessLogServiceImpl implements AccessLogService {

    private final AccessLogRepository accessLogRepository;
    private final DigitalKeyRepository digitalKeyRepository;
    private final GuestRepository guestRepository;
    private final KeyShareRequestRepository keyShareRequestRepository;

    public AccessLogServiceImpl(AccessLogRepository accessLogRepository,
                                DigitalKeyRepository digitalKeyRepository,
                                GuestRepository guestRepository,
                                KeyShareRequestRepository keyShareRequestRepository) {
        this.accessLogRepository = accessLogRepository;
        this.digitalKeyRepository = digitalKeyRepository;
        this.guestRepository = guestRepository;
        this.keyShareRequestRepository = keyShareRequestRepository;
    }

    @Override
    public AccessLog createLog(AccessLog log) {

        if (log.getAccessTime().isAfter(Instant.now())) {
            throw new IllegalArgumentException("Access time cannot be in the future");
        }

        DigitalKey key = digitalKeyRepository.findById(log.getDigitalKey().getId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Digital key not found"));

        if (!key.getActive()) {
            log.setResult("DENIED");
        } else {
            log.setResult("SUCCESS");
        }

        return accessLogRepository.save(log);
    }

    @Override
    public List<AccessLog> getLogsForGuest(Long guestId) {
        return accessLogRepository.findByGuestId(guestId);
    }

    @Override
    public List<AccessLog> getLogsForKey(Long keyId) {
        return accessLogRepository.findByDigitalKeyId(keyId);
    }
}
