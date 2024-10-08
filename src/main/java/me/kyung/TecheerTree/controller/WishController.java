package me.kyung.TecheerTree.controller;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import me.kyung.TecheerTree.domain.Wish;
import me.kyung.TecheerTree.dto.request.WishSaveRequest;
import me.kyung.TecheerTree.dto.response.WishDetailResponse;
import me.kyung.TecheerTree.dto.response.WishListResponse;
import me.kyung.TecheerTree.service.WishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/wishes")
@RequiredArgsConstructor
public class WishController {
    private final WishService wishService;

    @GetMapping("/{id}")
    public ResponseEntity<WishDetailResponse> findWish(@PathVariable("id") Long id){
        return ResponseEntity.ok(wishService.findWish(id));
    }

    @PostMapping
    public ResponseEntity<Wish> createWish(@RequestBody WishSaveRequest request) {
        return ResponseEntity.ok(wishService.createWish(request));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteWish(@PathVariable("id") Long id) {
        wishService.deleteWish(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/approve")
    public  ResponseEntity<Wish> approveWish(@PathVariable("id")Long id){
        wishService.updateWish(id, Wish.Status.APPROVED);
        return ResponseEntity.ok(wishService.updateWish(id, Wish.Status.APPROVED));
    }

    @PatchMapping("/{id}/reject")
    public  ResponseEntity<Wish> rejectWish(@PathVariable("id")Long id){
        wishService.updateWish(id, Wish.Status.REJECTED);
        return ResponseEntity.ok(wishService.updateWish(id, Wish.Status.REJECTED));
    }

    @GetMapping
    public ResponseEntity<List<WishListResponse>> findAllWish(
            @RequestParam Wish.Status status,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        List<WishListResponse> wishList = wishService.findAllWish(status, page, size);
        return ResponseEntity.ok(wishList);
    }




}