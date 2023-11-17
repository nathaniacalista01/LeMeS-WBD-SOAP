# LeMeS SOAP

## Introduction

LeMeS-soap adalah repositori yang menyediakan service backend untuk aplikasi LeMeS dengan protokol SOAP. Aplikasi ini dibuat dengan menggunakan bahasa Java. Aplikasi ini dibuat untuk memenuhi tugas besar mata kuliah Pemrograman Aplikasi Berbasis Web.

## Overview Feature

- SOAP Protocol
- Logging
- Dockerized
- Built with Maven

## Skema basis data

![skema](screenshots/20221202104105.png)  
Basis data terdiri atas dua tabel, logging dan premium_accounts. Tabel logging menyimpan informasi mengenai seluruh request yang dilakukan oleh user ke database SOAP, sedangkan tabel subscription menyimpan informasi mengenai siapa saja pengguna yang sudah melakukan request untuk upgrade ke akun premium_accounts

## Daftar endpoint

Semua endpoint berada pada /premium

- isPremium
  Untuk mengetahui apakah seorang user premium atau tidak 
- upgrade
  Digunakan oleh pengguna untuk request status premium
- updatePremiumStatus
  Digunakan oleh admin untuk memperbaharui status premium dari User
- deleteRequest
  Digunakan oleh admin ketika menolak request upgrade premium dari pengguna
- getPremiumStatus
  Digunakan untuk mengambil status request premium dari seorang pengguna
- getAllPremium
  Digunakan untuk mengambil seluruh data yang ada di tabel premium_accounts
- getPremiumPagination
  Untuk mendapatkan data premium dengan limit dan offset tertentu
- getTotalPremium
  Untuk mendapatkan total data dalam tabel premium_accounts


## Cara menjalankan aplikasi

1. Pastikan sudah terinstall docker
2. Lakukan docker compose dengan perintah `docker-compose up --build`

### Pembagian Tugas

1. Initial Project Setup: 13521139
2. Security: Logging and Api Key: 13521139
3. Upgrade Service: 13521139
4. Middleware : 13521139

#### Proudly Presented by Binosed, 2022
