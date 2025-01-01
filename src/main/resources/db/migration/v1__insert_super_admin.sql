DO
$$
    BEGIN
        IF NOT EXISTS (SELECT * FROM study_center.public.teacher WHERE username = 'abduraxim') THEN
            INSERT INTO study_center.public.teacher (name, surname, username, password, role)
            VALUES ('Abduraxim', 'Tursunboyev', 'abduraxim',
                    '$2b$12$.dwYNhrFjq1Pvv1/WmQm1umXPBDETx/rn35ox/1MkJ8dBdJ8N5NGm', 'ROLE_SUPERADMIN');
        END IF;
    END
$$;
