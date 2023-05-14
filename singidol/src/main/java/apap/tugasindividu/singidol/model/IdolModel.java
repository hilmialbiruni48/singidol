package apap.tugasindividu.singidol.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import java.time.LocalDateTime;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "idol")

public class IdolModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_idol",nullable = false)
    private Long idIdol;

    @NotNull
    @Size(max = 255)
    @Column(name = "nama_idol", nullable = false)
    private String namaIdol;

    @NotNull
    @Column(name = "jumlah_anggota", nullable = false)
    private Integer jumlahAnggota;

    @NotNull
    @Column(nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime tanggalDebut;

    @NotNull
    @Size(max = 255)
    @Column(name = "asal_negara", nullable = false)
    private String asalNegara;


    @OneToMany(mappedBy = "idol", fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private List<PenampilanKonserModel> listPenampilan;
}