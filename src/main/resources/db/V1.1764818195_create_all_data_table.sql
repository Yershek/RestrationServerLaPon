-- 1. Citizens (Граждане)
create table if not exists Citizens (
    id int generated always as identity primary key ,
    personal_id_number varchar not null ,
    last_name varchar not null ,
    first_name varchar not null ,
    middle_name varchar,
    date_of_birth date not null ,
    gender char not null,
    place_of_birth varchar,
    nationality varchar,
    password varchar,
    date_created timestamp default current_timestamp,
    unique (personal_id_number)
);

-- Agencies (Ведомства/Органы)
create table if not exists Agencies (
    id int generated always as identity primary key ,
    name varchar not null ,
    short_code varchar not null,
    unique (name, short_code)
);

-- DataRequestsLog (Журнал Запросов Данных)
create table if not exists DataRequestsLog (
    id bigint generated always as identity primary key ,
    requesting_agency_id int not null references Agencies(id),
    citizen_id int references Citizens(id),
    request_timestamp timestamp default CURRENT_TIMESTAMP,
    data_fields_requested text,
    purpose_code varchar
);

-- 2. CitizenContacts (Контактная информация)
create table if not exists CitizenContacts (
    id int generated always as identity primary key ,
    citizen_id int not null references Citizens(id),
    contact_type varchar not null,
    contact_value varchar not null ,
    is_primary boolean default false,
    unique (citizen_id, contact_type, contact_value)
);

-- 3. CitizenStatus (Юридический Статус)
create table if not exists CitizenStatus (
    id int generated always as identity primary key ,
    citizen_id int not null references Citizens(id),
    status_type varchar not null ,
    status_date date not null ,
    reference_document varchar,
    date_created timestamp default current_timestamp,
    unique (citizen_id, status_type)
);

-- 4. DocumentTypes (Справочник типов документов)
create table if not exists DocumentTypes (
    id int generated always as identity primary key ,
    name_ru varchar not null ,
    description TEXT,
    unique (name_ru)
);

-- 5. Documents (Детали документов)
create table if not exists Documents (
    id int generated always as identity primary key ,
    citizen_id int not null references Citizens(id),
    document_type_id int not null references DocumentTypes(id),
    series varchar,
    number varchar not null ,
    issue_date date not null ,
    expiry_date date,
    issuing_authority varchar not null ,
    is_active boolean default true,
    unique (document_type_id, series, number)
);

-- 6. DocumentChangesLog (Журнал Изменений Документов)
create table if not exists DocumentChangesLog (
    id bigint generated always as identity primary key ,
    document_id int not null references Documents(id),
    change_date timestamp default current_timestamp,
    change_type varchar not null ,
    agency_id int references Agencies(id),
    reason text
);

-- 7. GeographicHierarchy (Географическая Иерархия)
create table if not exists GeographicHierarchy (
    id int generated always as identity primary key ,
    parent_id int references GeographicHierarchy(id),
    name_ru varchar not null ,
    level varchar not null ,
    unique (parent_id, name_ru)
);

-- 8. Addresses (Физические адреса)
create table if not exists Addresses (
    id int generated always as identity primary key ,
    city_geo_id int not null references GeographicHierarchy(id),
    street varchar not null ,
    house_number varchar not null ,
    apartment_number varchar,
    postal_code varchar
);

-- 9. CitizenResidency (История регистрации)
create table if not exists CitizenResidency (
    id int generated always as identity primary key ,
    citizen_id int not null references Citizens(id),
    address_id int not null references Addresses(id),
    address_type varchar not null ,
    start_date date not null ,
    end_date date,
    date_registered timestamp default current_timestamp,
    unique (citizen_id, address_type, start_date)
);

-- 10. MaritalStatus (Справочник состояний)
create table if not exists MaritalStatus (
    id int generated always as identity primary key ,
    name varchar unique not null
);

-- 11. Marriages (Реестр Браков)
CREATE TABLE if not exists Marriages (
    id int generated always as identity primary key ,
    husband_id int not null references Citizens(id),
    wife_id int not null  references Citizens(id),
    marital_status_id int references MaritalStatus(id),
    marriage_date date not null ,
    registration_authority varchar not null ,
    document_series_number varchar unique ,
    is_active boolean default true,
    divorce_date date,
    unique (husband_id, wife_id, marriage_date)
);

-- 12. Relationships (Семейные Связи)
create table if not exists Relationships (
    id int generated always as identity primary key ,
    citizen_a_id int not null references Citizens(id),
    citizen_b_id int not null references Citizens(id),
    relationship_type varchar not null ,
    is_verified boolean default false,
    unique (citizen_a_id, citizen_b_id, relationship_type)
);