package com.floradex.service;


import java.util.List;



import com.floradex.entity.Zona;

public interface ZonaService {
	List<Zona> getAllZone();
	Zona getZonaById(Integer id);
	Zona saveZona(Zona zona);
	Zona updateZona(Integer id, Zona zona);
	void deleteZonaById(Integer id);

}
