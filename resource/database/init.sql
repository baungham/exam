CREATE TABLE t_class
(
    name TEXT PRIMARY KEY,
    desc TEXT,
    gradeName TEXT,
    createTime TEXT
);

CREATE TABLE t_exam
(
    id INTEGER PRIMARY KEY,
    name TEXT,
    semesterId INTEGER,
    gradeName TEXT,
    createTime TEXT,
    sortNum INTEGER,
    count INTEGER
);
CREATE TABLE t_grade
(
    name TEXT PRIMARY KEY,
    desc TEXT,
    createTime TEXT
);

CREATE TABLE t_printer_queue
(
    id INTEGER PRIMARY KEY,
    studentNo TEXT,
    gradeName TEXT,
    className TEXT,
    semesterId INTEGER,
    type INTEGER,
    status INTEGER,
    studentName TEXT,
    time TEXT
);



CREATE TABLE t_student
(
    studentNo TEXT,
    name TEXT,
    className TEXT,
    gradeName TEXT,
    oldClassName TEXT
);
CREATE UNIQUE INDEX sqlite_autoindex_t_student_1 ON t_student (studentNo);



CREATE TABLE t_exam_sience
(
    examId INTEGER,
    gradeName TEXT,
    className TEXT,
    semesterId INTEGER,
    studentNo TEXT,
    studentName TEXT,
    language INTEGER,
    languageOrder TEXT,
    mathematics INTEGER,
    mathematicsOrder TEXT,
    english INTEGER,
    englishOrder TEXT,
    physics INTEGER,
    physicsOrder TEXT,
    chemistry INTEGER,
    chemistryOrder TEXT,
    biology INTEGER,
    biologyOrder TEXT,
    scienceTotalAchievement INTEGER,
    scienceTotalAchievementOrder TEXT,
    scienceClassOrder TEXT,
    politics INTEGER,
    politicsOrder TEXT,
    history INTEGER,
    historyOrder TEXT,
    geography INTEGER,
    geographyOrder TEXT,
    literalTotalAchievement INTEGER,
    literalTotalAchievementOrder TEXT,
    literalClassOrder TEXT,
    infomation INTEGER,
    infomationOrder TEXT,
    general INTEGER,
    generalOrder TEXT,
    sports INTEGER,
    sportsOrder TEXT,
    music INTEGER,
    musicOrder TEXT,
    art INTEGER,
    artOrder TEXT,
    psychology INTEGER,
    psychologyOrder TEXT,
    count INTEGER DEFAULT 0,
    nineTotal INTEGER,
    nineTotalOrder TEXT
);