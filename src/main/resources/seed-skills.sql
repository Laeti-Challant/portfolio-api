-- Seed des compétences pour la portfolio-api
-- À exécuter une fois sur la base Supabase après le premier démarrage de l'API (qui crée les tables via JPA)

-- Nettoyage
DELETE FROM skill_competences;
DELETE FROM skills;

-- Langages
INSERT INTO skills (id, categorie) VALUES (1, 'Langages');
INSERT INTO skill_competences (skill_id, competence) VALUES
  (1, 'Java'),
  (1, 'Python'),
  (1, 'JavaScript (ES6+)'),
  (1, 'TypeScript'),
  (1, 'HTML'),
  (1, 'CSS'),
  (1, 'SQL');

-- Frameworks & librairies
INSERT INTO skills (id, categorie) VALUES (2, 'Frameworks & librairies');
INSERT INTO skill_competences (skill_id, competence) VALUES
  (2, 'Spring Boot'),
  (2, 'Angular'),
  (2, 'React'),
  (2, 'Vue.js'),
  (2, 'Nuxt 3'),
  (2, 'Node.js / Express'),
  (2, 'Django'),
  (2, 'Django REST Framework'),
  (2, 'TailwindCSS');

-- Bases de données
INSERT INTO skills (id, categorie) VALUES (3, 'Bases de données');
INSERT INTO skill_competences (skill_id, competence) VALUES
  (3, 'PostgreSQL'),
  (3, 'MySQL'),
  (3, 'Microsoft SQL Server');

-- Outils
INSERT INTO skills (id, categorie) VALUES (4, 'Outils');
INSERT INTO skill_competences (skill_id, competence) VALUES
  (4, 'Git / GitHub'),
  (4, 'Docker'),
  (4, 'Linux (Pop!_OS)'),
  (4, 'Postman'),
  (4, 'ESLint / Prettier'),
  (4, 'Render / Vercel'),
  (4, 'GitHub Actions');

-- Méthodologies
INSERT INTO skills (id, categorie) VALUES (5, 'Méthodologies');
INSERT INTO skill_competences (skill_id, competence) VALUES
  (5, 'Agile / Scrum'),
  (5, 'CI/CD');
