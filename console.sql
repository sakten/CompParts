USE TEST;
DROP TABLE IF EXISTS computer_parts;
CREATE TABLE computer_parts
(
    id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    name VARCHAR(128) NOT NULL,
    isNecessary boolean DEFAULT false  NOT NULL,
    quantity int DEFAULT 0 NOT NULL
);
CREATE UNIQUE INDEX computer_parts_id_uindex ON computer_parts (id);
CREATE UNIQUE INDEX computer_parts_name_uindex ON computer_parts (name);

INSERT INTO  computer_parts VALUES
  (1,'RAM KST 4GB',true,21),
  (2,'motherboardXV2',true,12),
  (3,'intel core i11 ',true,21),
  (4,'motherboard XV4',false,38),
  (5,'optical mouse RZ2',false,14),
  (6,'keyboard FOTON3',false,19),
  (7,'microphone ST3 turbo',false,144),
  (8,'headphones AD43',false,98),
  (9,'VR headset ZARYA',false,0),
  (10,'cooler WindForce',true,43),
  (11,'video card DarkScreen',true,67),
  (12,'sound card Sound of Silence',true,58),
  (13,'power button',true,55),
  (14,'CD rom',false,11),
  (15,'monitor Blind EYE',false,93),
  (16,'RAM KST 16GB',false,11),
  (17,'keyboard office',true,56),
  (18,'mouse office',true,55),
  (19,'headphones SoundBarrier',false,52),
  (20,'video card LightScreen',false,32),
  (21,'power block FireBorn',true,46),
  (22,'VR headset Paranoia',false,46),
  (23,'power block 700W',false,1),
  (24,'little computer part',true,33),
  (25,'medium computer part',true,52),
  (26,'big computer part',true,52)
;
