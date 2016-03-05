#!/bin/bash
if [ $# -eq 2 ]
  then
    INAMONTH=$(date --date="30 day" +"%Y-%m-%d")
    GO="INSERT INTO Flight (Status, AirportFrom, AirportTo, Start, Time, Airplane, CreatedAt) VALUES(1,1,2, '$INAMONTH 09:00', '00:59',2,now());
    INSERT INTO Flight (Status, AirportFrom, AirportTo, Start, Time, Airplane, CreatedAt) VALUES(1,1,3, '$INAMONTH 09:00', '02:30',2,now());
    INSERT INTO Flight (Status, AirportFrom, AirportTo, Start, Time, Airplane, CreatedAt) VALUES(1,1,4, '$INAMONTH 09:00', '02:20',2,now());
    INSERT INTO Flight (Status, AirportFrom, AirportTo, Start, Time, Airplane, CreatedAt) VALUES(1,1,5, '$INAMONTH 09:00', '02:55',2,now());
    INSERT INTO Flight (Status, AirportFrom, AirportTo, Start, Time, Airplane, CreatedAt) VALUES(1,1,6, '$INAMONTH 09:00', '08:30',2,now());
    INSERT INTO Flight (Status, AirportFrom, AirportTo, Start, Time, Airplane, CreatedAt) VALUES(1,1,7, '$INAMONTH 09:00', '11:50',2,now());
    INSERT INTO Flight (Status, AirportFrom, AirportTo, Start, Time, Airplane, CreatedAt) VALUES(1,1,8, '$INAMONTH 09:00', '02:10',2,now());
    INSERT INTO Flight (Status, AirportFrom, AirportTo, Start, Time, Airplane, CreatedAt) VALUES(1,1,9, '$INAMONTH 09:00', '04:15',2,now());
    INSERT INTO Flight (Status, AirportFrom, AirportTo, Start, Time, Airplane, CreatedAt) VALUES(1,1,10, '$INAMONTH 09:00', '02:35',2,now());
    INSERT INTO Flight (Status, AirportFrom, AirportTo, Start, Time, Airplane, CreatedAt) VALUES(1,1,11, '$INAMONTH 09:00', '02:00',2,now());
    INSERT INTO Flight (Status, AirportFrom, AirportTo, Start, Time, Airplane, CreatedAt) VALUES(1,1,12, '$INAMONTH 09:00', '02:20',2,now());
    INSERT INTO Flight (Status, AirportFrom, AirportTo, Start, Time, Airplane, CreatedAt) VALUES(1,1,13, '$INAMONTH 09:00', '02:25',2,now());
    INSERT INTO Flight (Status, AirportFrom, AirportTo, Start, Time, Airplane, CreatedAt) VALUES(1,1,14, '$INAMONTH 09:00', '02:25',2,now());
    INSERT INTO Flight (Status, AirportFrom, AirportTo, Start, Time, Airplane, CreatedAt) VALUES(1,1,15, '$INAMONTH 09:00', '14:15',2,now());
    INSERT INTO Flight (Status, AirportFrom, AirportTo, Start, Time, Airplane, CreatedAt) VALUES(1,1,16, '$INAMONTH 09:00', '13:15',2,now());
    INSERT INTO Flight (Status, AirportFrom, AirportTo, Start, Time, Airplane, CreatedAt) VALUES(1,1,17, '$INAMONTH 09:00', '10:50',2,now());"


    BACK="INSERT INTO Flight (Status, AirportFrom, AirportTo, Start, Time, Airplane, CreatedAt) VALUES(1,2,1, '$INAMONTH 19:00', '00:59',2,now());
    INSERT INTO Flight (Status, AirportFrom, AirportTo, Start, Time, Airplane, CreatedAt) VALUES(1,3,1, '$INAMONTH 20:00', '02:30',2,now());
    INSERT INTO Flight (Status, AirportFrom, AirportTo, Start, Time, Airplane, CreatedAt) VALUES(1,4,1, '$INAMONTH 14:00', '02:20',2,now());
    INSERT INTO Flight (Status, AirportFrom, AirportTo, Start, Time, Airplane, CreatedAt) VALUES(1,5,1, '$INAMONTH 15:00', '02:55',2,now());
    INSERT INTO Flight (Status, AirportFrom, AirportTo, Start, Time, Airplane, CreatedAt) VALUES(1,6,1, '$INAMONTH 17:00', '08:30',2,now());
    INSERT INTO Flight (Status, AirportFrom, AirportTo, Start, Time, Airplane, CreatedAt) VALUES(1,7,1, '$INAMONTH 06:00', '11:50',2,now());
    INSERT INTO Flight (Status, AirportFrom, AirportTo, Start, Time, Airplane, CreatedAt) VALUES(1,8,1, '$INAMONTH 09:00', '02:10',2,now());
    INSERT INTO Flight (Status, AirportFrom, AirportTo, Start, Time, Airplane, CreatedAt) VALUES(1,9,1, '$INAMONTH 11:00', '04:15',2,now());
    INSERT INTO Flight (Status, AirportFrom, AirportTo, Start, Time, Airplane, CreatedAt) VALUES(1,10,1, '$INAMONTH 12:00', '02:35',2,now());
    INSERT INTO Flight (Status, AirportFrom, AirportTo, Start, Time, Airplane, CreatedAt) VALUES(1,11,1, '$INAMONTH 13:00', '02:00',2,now());
    INSERT INTO Flight (Status, AirportFrom, AirportTo, Start, Time, Airplane, CreatedAt) VALUES(1,12,1, '$INAMONTH 15:00', '02:20',2,now());
    INSERT INTO Flight (Status, AirportFrom, AirportTo, Start, Time, Airplane, CreatedAt) VALUES(1,13,1, '$INAMONTH 16:00', '02:25',2,now());
    INSERT INTO Flight (Status, AirportFrom, AirportTo, Start, Time, Airplane, CreatedAt) VALUES(1,14,1, '$INAMONTH 11:00', '02:25',2,now());
    INSERT INTO Flight (Status, AirportFrom, AirportTo, Start, Time, Airplane, CreatedAt) VALUES(1,15,1, '$INAMONTH 12:00', '14:15',2,now());
    INSERT INTO Flight (Status, AirportFrom, AirportTo, Start, Time, Airplane, CreatedAt) VALUES(1,16,1, '$INAMONTH 13:00', '13:15',2,now());
    INSERT INTO Flight (Status, AirportFrom, AirportTo, Start, Time, Airplane, CreatedAt) VALUES(1,17,1, '$INAMONTH 14:00', '10:50',2,now());"

    mysql --user=$1 --password=$2 flight -e "$GO"
    mysql --user=$1 --password=$2 flight -e "$BACK"
fi
