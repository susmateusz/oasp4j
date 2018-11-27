CREATE TABLE Special (
  id BIGINT NOT NULL AUTO_INCREMENT,
  name VARCHAR(255) UNIQUE,
  startingDay INTEGER NULL,
  endingDay INTEGER NULL,
  --startingDay VARCHAR(255) NULL,
  --endingDay VARCHAR(255) NULL,
  startingHour INTEGER NOT NULL,
  endingHour INTEGER NOT NULL,
  offerId BIGINT NOT NULL,
  specialPrice DECIMAL(19, 2),
  modificationCounter INTEGER NOT NULL
  --, revision INTEGER NULL
  , CONSTRAINT PK_Special PRIMARY KEY (id)
  , CONSTRAINT FK_Special_offerId FOREIGN KEY(offerId) REFERENCES Offer(id) NOCHECK
  -- ,CONSTRAINT Chk_Special_startDay CHECK (startingDay >= 0 AND startingDay <7)
  --, CONSTRAINT Chk_Special_endDay CHECK (endingDay >= 0 AND endingDay <7)
  , CONSTRAINT Chk_Special_startHour CHECK (startingHour >= 0 AND startingHour < 24)
  , CONSTRAINT Chk_Special_endHour CHECK (endingHour >= 0 AND endingHour < 24)
  --, CONSTRAINT Chk_Special_startBeforeEnd CHECK (startingDay * 24 + startingHour <= endingDay * 24 + endingHour)
);