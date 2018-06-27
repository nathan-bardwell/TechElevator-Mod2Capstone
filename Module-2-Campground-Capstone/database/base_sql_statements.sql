--Requirement #1
SELECT  *
FROM park
ORDER BY name;

--Recuirement #2
SELECT campground.*
FROM campground
JOIN park
  ON campground.park_id = park.park_id
WHERE park_id = ?;

--Requirement #3
SELECT site.*
FROM site
JOIN campground camp
  ON site.campground_id = camp.campground_id
JOIN reservation rsrv
  ON site.site_id = rsrv.site_id
WHERE ((? NOT BETWEEN rsrv.from_date AND rsrv.to_date) AND
       (? NOT BETWEEN rsrv.from_date AND rsrv.to_date))
      AND camp.campground_id = 2;

SELECT site.site_id, camp.daily_fee
FROM site
JOIN campground camp
  ON site.campground_id = camp.campground_id


--Requirement #4
INSERT INTO reservation (site_id, name, from_date, to_date, create_date)
VALUES (?, ?, ?, ?, current_date);

SELECT reservation_id
FROM reservation 
WHERE name = ? and site_id = ?;

--Requirement #5 (BONUS)

--Requirement #6 (BONUS)






