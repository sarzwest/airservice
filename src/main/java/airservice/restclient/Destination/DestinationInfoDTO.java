/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package airservice.restclient.Destination;

import java.util.List;

/**
 *
 * @author Tomas "sarzwest" Jiricek
 */
public class DestinationInfoDTO {

    private String status;
    private List<Results> results;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Results> getResults() {
        return results;
    }

    public void setResults(List<Results> results) {
        this.results = results;
    }

    class Results {

        private Geometry geometry;

        public Results() {
        }

        public Geometry getGeometry() {
            return geometry;
        }

        public void setGeometry(Geometry geometry) {
            this.geometry = geometry;
        }
    }

    class Geometry {
        private Location location;

        public Geometry() {
        }

        public Location getLocation() {
            return location;
        }

        public void setLocation(Location location) {
            this.location = location;
        }
    }
    
    public static class Location{
        private float lat;
        private float lng;

        public Location() {
        }

        public float getLat() {
            return lat;
        }

        public void setLat(float lat) {
            this.lat = lat;
        }

        public float getLng() {
            return lng;
        }

        public void setLng(float lng) {
            this.lng = lng;
        }
    }
}
//{
//   "results" : [
//      {
//         "address_components" : [
//            {
//               "long_name" : "Prague",
//               "short_name" : "Prague",
//               "types" : [ "locality", "political" ]
//            },
//            {
//               "long_name" : "Hlavní město Praha",
//               "short_name" : "Hlavní město Praha",
//               "types" : [ "administrative_area_level_2", "political" ]
//            }
//         ],
//         "formatted_address" : "Prague, Czech Republic",
//         "geometry" : {
//            "bounds" : {
//               "northeast" : {
//                  "lat" : 50.1774031,
//                  "lng" : 14.7067946
//               },
//               "southwest" : {
//                  "lat" : 49.94193629999999,
//                  "lng" : 14.2244534
//               }
//            },
//            "location" : {
//               "lat" : 50.0755381,
//               "lng" : 14.4378005
//            },
//            "location_type" : "APPROXIMATE",
//            "viewport" : {
//               "northeast" : {
//                  "lat" : 50.1774031,
//                  "lng" : 14.7067946
//               },
//               "southwest" : {
//                  "lat" : 49.94193629999999,
//                  "lng" : 14.2244534
//               }
//            }
//         },
//         "types" : [ "locality", "political" ]
//      }
//   ],
//   "status" : "OK"
//}
