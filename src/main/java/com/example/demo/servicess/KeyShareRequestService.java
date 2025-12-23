package com.example.demo.service;

import java.util.List;

import com.example.demo.model.KeyShareRequest;

public interface KeyShareRequestService {

    KeyShareRequest createShareRequest(KeyShareRequest request);

    List<KeyShareRequest> getRequestsSharedBy(Long guestId);

    List<KeyShareRequest> getRequestsSharedWith(Long guestId);
}
