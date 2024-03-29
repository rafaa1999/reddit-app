package com.rafaa.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Post {
   @Id
   @GeneratedValue(strategy = GenerationType.SEQUENCE)
   private Long postId;
   @NotBlank(message = "Post Name cannot be empty or null")
   private String postName;
   @Nullable
   @Lob
   private String url;
   private String description;
   private int voteCount;
   @ManyToOne(fetch = FetchType.LAZY)
   @JoinColumn(name = "userId", referencedColumnName = "userId")
   private User user;
   private Instant createdDate;
   @ManyToOne(fetch = FetchType.LAZY)
   @JoinColumn(name = "id", referencedColumnName = "id")
   private Subreddit subreddit;
}
