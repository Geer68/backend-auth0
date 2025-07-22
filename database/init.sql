CREATE TABLE usuarios (
  id                  BIGSERIAL    PRIMARY KEY,
  auth0_id            TEXT         NOT NULL UNIQUE,
  nombre              TEXT         NOT NULL,
  apellido            TEXT         NOT NULL,
  email               TEXT         NOT NULL UNIQUE,
  telefono            TEXT,
  dni                 TEXT         UNIQUE,
  fecha_nacimiento    DATE,
  creado_en           TIMESTAMPTZ  NOT NULL DEFAULT NOW(),
  actualizado_en      TIMESTAMPTZ  NOT NULL DEFAULT NOW()
);


CREATE OR REPLACE FUNCTION trg_set_actualizado_en()
RETURNS TRIGGER AS $$
BEGIN
  NEW.actualizado_en := NOW();
  RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER usuarios_updated_at
  BEFORE UPDATE ON usuarios
  FOR EACH ROW EXECUTE FUNCTION trg_set_actualizado_en();



CREATE TABLE pacientes (
  id              BIGSERIAL     PRIMARY KEY,
  usuario_id      BIGINT       REFERENCES usuarios(id) ON DELETE CASCADE,
  obra_social     TEXT         NOT NULL,
  telefono_emergencia TEXT
);

CREATE TABLE administradores (
  id              BIGSERIAL     PRIMARY KEY,
  usuario_id      BIGINT       REFERENCES usuarios(id) ON DELETE CASCADE,
  fecha_ingreso   TIMESTAMPTZ  NOT NULL DEFAULT NOW()
);


CREATE TABLE especialidades (
  id      BIGSERIAL     PRIMARY KEY,
  nombre  TEXT       NOT NULL UNIQUE
);



CREATE TABLE dentistas (
  id              BIGSERIAL     PRIMARY KEY,
  usuario_id      BIGINT       REFERENCES usuarios(id) ON DELETE CASCADE,
  especialidad_id     INT          NOT NULL
                       REFERENCES especialidades(id),
  matricula           TEXT         UNIQUE
);



CREATE TYPE estado_turno AS ENUM ('PROGRAMADO','TERMINADO','CANCELADO');

CREATE TABLE turnos (
  id                   BIGSERIAL       PRIMARY KEY,
  paciente_id          BIGINT          NOT NULL REFERENCES pacientes(id),
  dentista_id          BIGINT          NOT NULL REFERENCES dentistas(id),
  fecha_hora           TIMESTAMPTZ     NOT NULL,
  creado_en            TIMESTAMPTZ     NOT NULL DEFAULT NOW(),
  estado               estado_turno    NOT NULL DEFAULT 'PROGRAMADO',
  notas_tratamiento    TEXT,
  comentarios          TEXT,
  evolucion            TEXT
);

CREATE INDEX idx_turnos_por_dentista_fecha
  ON turnos(dentista_id, fecha_hora);

CREATE INDEX idx_turnos_por_paciente
  ON turnos(paciente_id);


CREATE TABLE disponibilidades_dentista (
  id             BIGSERIAL     PRIMARY KEY,
  dentista_id    BIGINT        NOT NULL
                   REFERENCES dentistas(id) ON DELETE CASCADE,
  dia_semana     SMALLINT      NOT NULL CHECK (dia_semana BETWEEN 0 AND 6),  -- 0=Dom,6=Sáb
  hora_inicio    TIME          NOT NULL,
  hora_fin       TIME          NOT NULL,
  CHECK (hora_inicio < hora_fin)
);

CREATE UNIQUE INDEX ux_disp_por_dentista_dia_hora
  ON disponibilidades_dentista(dentista_id, dia_semana, hora_inicio, hora_fin);


--INSERT INTO usuarios (auth0_id, nombre, apellido, email, telefono, dni, fecha_nacimiento)
--VALUES ('auth0|testuser', 'Test', 'Usuario', 'test@example.com', '123456789', '12345678', '1990-01-01');
--
--INSERT INTO especialidades (nombre) VALUES ('Odontología General'), ('Ortodoncia'), ('Endodoncia');
--
--INSERT INTO dentistas (usuario_id, especialidad_id, matricula)
--VALUES (1, 1, 'MATRICULA-001');