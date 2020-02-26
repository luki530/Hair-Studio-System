-- phpMyAdmin SQL Dump
-- version 4.8.5
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Czas generowania: 26 Lut 2020, 14:00
-- Wersja serwera: 8.0.13-4
-- Wersja PHP: 7.2.24-0ubuntu0.18.04.3

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Baza danych: `zzV412UPYS`
--

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `users`
--

CREATE TABLE `users` (
  `login` varchar(12) CHARACTER SET utf8 COLLATE utf8_polish_ci NOT NULL,
  `role` text CHARACTER SET utf8 COLLATE utf8_polish_ci NOT NULL,
  `first_name` text CHARACTER SET utf8 COLLATE utf8_polish_ci NOT NULL,
  `last_name` text CHARACTER SET utf8 COLLATE utf8_polish_ci NOT NULL,
  `email` text CHARACTER SET utf8 COLLATE utf8_polish_ci NOT NULL,
  `phone_number` text CHARACTER SET utf8 COLLATE utf8_polish_ci NOT NULL,
  `password` text CHARACTER SET utf8 COLLATE utf8_polish_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_polish_ci;

--
-- Zrzut danych tabeli `users`
--

INSERT INTO `users` (`login`, `role`, `first_name`, `last_name`, `email`, `phone_number`, `password`) VALUES
('admin', 'ADMIN', 'admin', 'admin', 'admin@twojfryzjer.pl', '997', 'admin'),
('as', 'CLIENT', 'as', 'as', 'siema', 'as', 'as'),
('eee', 'EMPLOYEE', 'eee', 'eee', 'eee@o2.com', 'ee', 'eee'),
('janek12', 'CLIENT', 'janek', 'wisniewksi', 'siema@o2.pl', 'o1020o120o120o102', 'janek12'),
('jarek', 'CLIENT', 'jarek', 'zelechowski', '997@112.pl', '0700', 'jarek'),
('luki530', 'CLIENT', 'lukasz', 'oszczypala', 'lukasz.oszczypala@gmail.com', '609600002', 'luki530'),
('pracownik1', 'EMPLOYEE', 'Jan', 'Kowalski', 'a@a.pl', '999', 'pracownik'),
('pracownik2', 'EMPLOYEE', 'pawel', 'pawel', 'sss', 'sss', 'pracownik2'),
('qw', 'CLIENT', 'qw', 'qw', 'qw@o2.pl', 'qw', 'qw'),
('qwa', 'EMPLOYEE', 'qw', 'qw', 'qw@onet.pl', 'qw', 'qw');

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `visits`
--

CREATE TABLE `visits` (
  `id` int(11) NOT NULL,
  `start_time` timestamp NOT NULL,
  `end_time` timestamp NOT NULL,
  `service` text CHARACTER SET utf8 COLLATE utf8_polish_ci NOT NULL,
  `creator` varchar(12) CHARACTER SET utf8 COLLATE utf8_polish_ci DEFAULT NULL,
  `employee` varchar(12) CHARACTER SET utf8 COLLATE utf8_polish_ci NOT NULL,
  `client` varchar(12) CHARACTER SET utf8 COLLATE utf8_polish_ci DEFAULT NULL,
  `confirmed` tinyint(1) NOT NULL DEFAULT '0',
  `busy` tinyint(1) NOT NULL DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_polish_ci;

--
-- Zrzut danych tabeli `visits`
--

INSERT INTO `visits` (`id`, `start_time`, `end_time`, `service`, `creator`, `employee`, `client`, `confirmed`, `busy`) VALUES
(79, '2020-01-24 04:46:00', '2020-01-24 05:16:00', 'Value Packages For Men', 'admin', 'pracownik2', 'luki530', 1, 1),
(80, '2020-01-23 17:29:00', '2020-01-23 19:29:00', 'Color For Women', 'janek12', 'pracownik1', 'janek12', 1, 1),
(82, '2020-01-30 08:33:00', '2020-01-30 09:18:00', 'Cut And Style For Men', 'admin', 'pracownik1', 'janek12', 1, 1);

--
-- Indeksy dla zrzutów tabel
--

--
-- Indeksy dla tabeli `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`login`);

--
-- Indeksy dla tabeli `visits`
--
ALTER TABLE `visits`
  ADD PRIMARY KEY (`id`),
  ADD KEY `creator` (`creator`),
  ADD KEY `client` (`client`),
  ADD KEY `employee` (`employee`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT dla tabeli `visits`
--
ALTER TABLE `visits`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=83;

--
-- Ograniczenia dla zrzutów tabel
--

--
-- Ograniczenia dla tabeli `visits`
--
ALTER TABLE `visits`
  ADD CONSTRAINT `visits_ibfk_1` FOREIGN KEY (`creator`) REFERENCES `users` (`login`) ON DELETE CASCADE ON UPDATE RESTRICT,
  ADD CONSTRAINT `visits_ibfk_2` FOREIGN KEY (`client`) REFERENCES `users` (`login`) ON DELETE CASCADE ON UPDATE RESTRICT,
  ADD CONSTRAINT `visits_ibfk_3` FOREIGN KEY (`employee`) REFERENCES `users` (`login`) ON DELETE CASCADE ON UPDATE RESTRICT;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
