DROP TABLE IF EXISTS Game;
CREATE TABLE IF NOT EXISTS Game (
    id INT,
    home_or_away char(4),
    opponent varchar(255),
    date date,
    points_for INT,
    points_against INT,
    PRIMARY KEY (id)
) engine = InnoDB;