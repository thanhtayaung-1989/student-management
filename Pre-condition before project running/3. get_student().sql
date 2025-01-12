use `student-db`;
DELIMITER $$
CREATE PROCEDURE get_students(
    IN limit_value INT,
    IN offset_value INT
)
BEGIN
	SELECT * FROM student
    ORDER BY studentid
    LIMIT limit_value OFFSET offset_value;
END $$
DELIMITER ;

