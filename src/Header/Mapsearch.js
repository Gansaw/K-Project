import React, { useState, useCallback, useRef, useEffect } from 'react';
import { GoogleMap, LoadScript, Marker, InfoWindow, Autocomplete } from '@react-google-maps/api';
import style from '../Airport/Airport.module.css';
import { useNavigate, useLocation } from 'react-router-dom';


const Mapsearch = () => {
  const location = useLocation();
  const params = new URLSearchParams(location.search);
  const searchQuery = params.get('search');
  const [selectedPlace, setSelectedPlace] = useState(null);
  const [center, setCenter] = useState({ lat: 37.5400456, lng: 126.9921017 });
  const autoCompleteRef = useRef(null);
  const [searchTerm, setSearchTerm] = useState();
  const [searchResults, setSearchResults] = useState([]);
  
  console.log(searchQuery)

  
  useEffect(() => {
    
    console.log(searchTerm)
    if (searchTerm && window.google) {
      const service = new window.google.maps.places.AutocompleteService();
      service.getQueryPredictions({ input: searchTerm }, (predictions, status) => {
        if (status === window.google.maps.places.PlacesServiceStatus.OK) {
          setSearchResults(predictions);
          
        }
      });
    }
  }, [searchTerm]);

  useEffect(() => {
    setSearchTerm(searchQuery);
    if (searchQuery && window.google) {
        const geocoder = new window.google.maps.Geocoder();
        geocoder.geocode({ 'address': searchQuery }, (results, status) => {
          console.log('sta',status)
            if (status === 'OK') {
              // 지도의 중심을 검색된 위치로 업데이트
              setCenter(results[0].geometry.location.toJSON());
              
              // 선택된 장소도 업데이트 (옵션)
              setSelectedPlace({
                name: results[0].formatted_address,
                address: results[0].formatted_address // 또는 다른 필요한 정보
              });
            } else {
              console.error('Geocode was not successful for the following reason: ' + status);
            }
            console.log("Geocode results:", results, status);
        });
    }
}, [searchQuery]);
  

  const onLoad = useCallback((autoComplete) => {
    autoCompleteRef.current = autoComplete;
    console.log('auto',autoCompleteRef.current)
    //onPlaceChanged();
  }, []);

  const onPlaceChanged = () => {
    if (autoCompleteRef.current != null) {
      const place = autoCompleteRef.current.getPlace();
  
      if (place.geometry) {
        const lat = place.geometry.location.lat();
        const lng = place.geometry.location.lng();
        setCenter({lat, lng});
        setSelectedPlace({name: place.name, lat, lng});
      } else {
        console.log("No location data available for the selected place.");
      }
    }
  };
 

  
  const handleFocus = () => {
    const autocomplete = autoCompleteRef.current;
    if (autocomplete) {
      // Listen for place_changed event on Autocomplete
      autocomplete.addListener('place_changed', () => {
        const place = autocomplete.getPlace();
        if (place.geometry) {
          const lat = place.geometry.location.lat();
          const lng = place.geometry.location.lng();
          setCenter({ lat, lng });
          setSelectedPlace({ name: place.name, lat, lng });
        } else {
          console.log("No location data available for the selected place.");
        }
      });
    }
  };
  
  const navi = useNavigate();

  const handleSearch = () => {

    console.log(searchResults, searchQuery)
    if (searchResults && searchResults.length > 0) {
      const firstResult = searchResults[0];
      // 여기서는 PlacesService를 사용하여 첫 번째 결과에 대한 자세한 정보를 가져와야 합니다.
      const service = new window.google.maps.places.PlacesService(document.createElement("div"));
      service.getDetails({ placeId: firstResult.place_id }, (place, status) => {
        if (status === window.google.maps.places.PlacesServiceStatus.OK && place.geometry) {
          const lat = place.geometry.location.lat();
          const lng = place.geometry.location.lng();
          setCenter({ lat, lng });
          setSelectedPlace({ name: place.name, lat, lng });
          navi(`/mapsearch?search=${firstResult.description}`);
        }
      });
    }
  };

 

  return (
    <div className={style.map}>
      <LoadScript
        googleMapsApiKey="AIzaSyDv5r3BUbGGp9O1J36N1mz8RKHYiTaMTXw"
        libraries={['places']}
      >
        <div className={style.searchContainer}>
          <button type="button" className={style.mapbt} onClick={() => navi('/')}>메인화면으로 가기</button>
          <div className={style.search}>
            <Autocomplete
              onLoad={onLoad}
              onPlaceChanged={onPlaceChanged}
            >
              <div className={style.searchInput}>
                <div>
                <input
                  type="search"
                  placeholder="공항이름이나 공항코드를 입력하세요."
                  value={searchTerm}
                  onChange={(e) => setSearchTerm(e.target.value)}
                  onFocus={handleFocus}
                  onKeyDown={(e) => {
                    if (e.key === "Enter") handleSearch();
                  }}
                />
                </div>
                <div>
                  <button className={style.mapbt} onClick={handleSearch}>검색</button>
                </div>
              </div>
            </Autocomplete>
          </div>
        </div>
        <GoogleMap
          mapContainerStyle={{ width: '100%', height: '100%' }}
          center={center}
          zoom={12}
        >
          {selectedPlace && (
            <Marker 
              position={center}
              onClick={() => setSelectedPlace(null)}
            />
          )}
          {selectedPlace && (
            <InfoWindow
              position={center}
              onCloseClick={() => setSelectedPlace(null)}
            >
              <div>
                <h6>{selectedPlace.name}</h6>
                <p>{selectedPlace.address}</p>
              </div>
            </InfoWindow>
          )}
        </GoogleMap>
      </LoadScript>
    </div>
  );
}

export default Mapsearch;
