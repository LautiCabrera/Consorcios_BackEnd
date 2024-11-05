package com.consorcio.servicios.Utils;

import com.consorcio.servicios.Entity.Location;
import com.consorcio.servicios.Entity.Country;
import com.consorcio.servicios.Entity.Province;
import com.consorcio.servicios.Repository.LocationRepository;
import com.consorcio.servicios.Repository.CountryRepository;
import com.consorcio.servicios.Repository.ProvinceRepository;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.List;

@Component
public class LocalidadesSeeder implements CommandLineRunner {

    @Autowired
    private LocationRepository locationRepository;

    @Autowired
    private ProvinceRepository provinceRepository;

    @Autowired
    private CountryRepository countryRepository;

    @Override
    @Transactional
    public void run(String... args) {
        try {
            // Verificar si ya existen datos
            if (countryRepository.count() == 0) {
                // Crear y guardar Argentina
                Country argentina = new Country();
                argentina.setName("Argentina");
                argentina = countryRepository.save(argentina);

                // Descargar datos de departamentos
                RestTemplate restTemplate = new RestTemplate();
                String jsonResponse = restTemplate.getForObject(
                        "https://infra.datos.gob.ar/georef/departamentos.json",
                        String.class);
                JSONObject jsonObject = new JSONObject(jsonResponse);
                JSONArray departamentosArray = jsonObject.getJSONArray("departamentos");

                List<Location> locationList = new ArrayList<>();

                for (int i = 0; i < departamentosArray.length(); i++) {
                    JSONObject departamentoJson = departamentosArray.getJSONObject(i);
                    String locationName = departamentoJson.getString("nombre");
                    JSONObject provinciaJson = departamentoJson.getJSONObject("provincia");
                    String provinceName = provinciaJson.getString("nombre");

                    // Buscar o crear la provincia
                    Province province = provinceRepository.findByName(provinceName);
                    if (province == null) {
                        province = new Province();
                        province.setName(provinceName);
                        province.setIdCountry(argentina.getIdCountry());
                        province = provinceRepository.save(province);
                    }

                    // Crear la localidad
                    Location location = new Location();
                    location.setName(locationName);
                    location.setIdProvince(province.getIdProvince());
                    locationList.add(location);
                }

                // Guardar todas las localidades
                locationRepository.saveAll(locationList);
            }
        } catch (Exception e) {
            System.err.println("Error al descargar y guardar los datos: " + e.getMessage());
            e.printStackTrace();
        }
    }

}
