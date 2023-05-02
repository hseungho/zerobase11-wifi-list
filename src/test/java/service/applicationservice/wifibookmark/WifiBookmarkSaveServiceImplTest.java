package service.applicationservice.wifibookmark;

import global.config.InstanceFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import service.controller.dto.WifiBookmarkSaveRequestDto;
import service.controller.dto.WifiBookmarkSaveResponseDto;
import service.entity.Bookmark;
import service.repository.BookmarkRepository;
import service.repository.WifiBookmarkRepository;

import java.util.ArrayList;
import java.util.List;

class WifiBookmarkSaveServiceImplTest {

    public final BookmarkRepository bookmarkRepository = InstanceFactory.BookmarkRepositoryFactory.getInstance();
    private final WifiBookmarkSaveService wifiBookmarkSaveService = InstanceFactory.WifiBookmarkSaveServiceFactory.getInstance();
    private final WifiBookmarkRepository wifiBookmarkRepository = InstanceFactory.WifiBookmarkRepositoryFactory.getInstance();

    @Test
    void test_saveWifiBookmark() {
        String wifiId = "---EP000001";
        Bookmark test_bookmark = bookmarkRepository.save(Bookmark.of("tester", 13));

        WifiBookmarkSaveResponseDto save = wifiBookmarkSaveService.saveWifiBookmark(WifiBookmarkSaveRequestDto.of(wifiId, test_bookmark.getId()));

        wifiBookmarkRepository.deleteById(save.getId());
        bookmarkRepository.delete(test_bookmark);
    }

//    @Test
    void test_save_five_wifiBookmark() {
        List<String> wifiIds = new ArrayList<>();
        wifiIds.add("---EP000001");
        wifiIds.add("---EP000003");
        wifiIds.add("---EP000004");
        wifiIds.add("---EP000008");
        wifiIds.add("---EP000013");

        List<Long> bookmarkIds = new ArrayList<>();
        bookmarkIds.add(1L);
        bookmarkIds.add(2L);
        bookmarkIds.add(3L);
        bookmarkIds.add(4L);
        bookmarkIds.add(5L);

        List<WifiBookmarkSaveResponseDto> dtos = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            WifiBookmarkSaveResponseDto save = wifiBookmarkSaveService.saveWifiBookmark(WifiBookmarkSaveRequestDto.of(wifiIds.get(i), bookmarkIds.get(i)));
            dtos.add(save);
        }

        Assertions.assertFalse(dtos.isEmpty());
        Assertions.assertEquals(5, dtos.size());

        dtos.forEach(dto -> wifiBookmarkRepository.deleteById(dto.getId()));
    }
}