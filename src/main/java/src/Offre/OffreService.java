package src.Offre;


import java.util.List;
import java.util.UUID;

import src.DTO.Offer.OfferCreateDTO;
import src.DTO.Offer.OfferResponseDTO;
import src.DTO.Offer.OfferUpdateDTO;
import src.RH.RH;


public interface OffreService {

//	    OfferResponseDTO createOffer(RH rh, OfferCreateDTO dto);

	    List<OfferResponseDTO> getOffersByRH(RH rh);
	    List<OfferResponseDTO> getOffersBySociete(String nomSociete);
	    List<OfferResponseDTO> getOffers();

	    //OfferResponseDTO updateOffer(int offerId, OfferUpdateDTO dto, RH rh);
	    OfferResponseDTO updateOffer(int offerId, OfferUpdateDTO dto, RH rh);
	    void deleteOffer(int offerId);
	    OfferResponseDTO createOffer(UUID rhId, OfferCreateDTO dto);



}
