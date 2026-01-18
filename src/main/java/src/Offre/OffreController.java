package src.Offre;

import java.util.UUID;

import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.persistence.EntityNotFoundException;
import src.DTO.Offer.OfferCreateDTO;
import src.DTO.Offer.OfferResponseDTO;
import src.DTO.Offer.OfferUpdateDTO;
import src.RH.RH;
import src.RH.RhRepository;

@RestController
@RequestMapping("/offres")
public class OffreController {	

	    private final OffreService offreService;
	    private final RhRepository rhRepository;

	    public OffreController(OffreService offreService, RhRepository rhRepository) {
	        this.offreService = offreService;
	        this.rhRepository = rhRepository;
	    }

	    @PostMapping("/create/{rhId}")
	    public ResponseEntity<OfferResponseDTO> create(
	            @PathVariable UUID rhId,
	            @RequestBody OfferCreateDTO dto) {

	        OfferResponseDTO saved = offreService.createOffer(rhId, dto);
	        return ResponseEntity.ok(saved);
	    }
	    
	    @PutMapping("/update/{offerId}/rh/{rhId}")
	    public ResponseEntity<OfferResponseDTO> updateOffer(
	            @PathVariable int offerId,
	            @PathVariable UUID rhId,
	            @RequestBody OfferUpdateDTO dto) {

	        RH rh = rhRepository.findById(rhId)
	                .orElseThrow(() -> new EntityNotFoundException("RH introuvable"));

	        OfferResponseDTO updated =
	                offreService.updateOffer(offerId, dto, rh);

	        return ResponseEntity.ok(updated);
	    }


	    
	    
	}
