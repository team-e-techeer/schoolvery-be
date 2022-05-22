package net.schoolvery.schoolveryserver.domain.chat.service;

import java.util.UUID;

public interface MemberService {
    void addMembers(UUID room_id, UUID member_id);
}
