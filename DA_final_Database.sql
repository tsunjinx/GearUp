-- =============================================
-- Shoe Store Management System Database
-- Database: DA_final
-- SQL Server Database Creation Script
-- =============================================

-- Create Database
USE master;
GO

IF EXISTS (SELECT name FROM sys.databases WHERE name = 'DA_final')
BEGIN
    ALTER DATABASE DA_final SET SINGLE_USER WITH ROLLBACK IMMEDIATE;
    DROP DATABASE DA_final;
END
GO

CREATE DATABASE DA_final;
GO

USE DA_final;
GO

-- =============================================
-- CREATE TABLES
-- =============================================

-- 1. Employee Table (NhanVien)
CREATE TABLE NhanVien (
    SoThuTu INT IDENTITY(1,1) PRIMARY KEY,
    IDNhanVien VARCHAR(10) NOT NULL UNIQUE,
    Ten NVARCHAR(50) NOT NULL,
    Tuoi INT CHECK (Tuoi >= 18 AND Tuoi <= 65),
    GioiTinh BIT NOT NULL, -- 1: Male, 0: Female
    DiaChi NVARCHAR(200),
    VaiTro BIT NOT NULL, -- 1: Manager, 0: Employee
    TenDangNhap VARCHAR(50) NOT NULL UNIQUE,
    MatKhau VARCHAR(50) NOT NULL,
    NgayTao DATETIME DEFAULT GETDATE(),
    NgaySua DATETIME DEFAULT GETDATE(),
    TrangThai BIT DEFAULT 1 -- 1: Active, 0: Inactive
);

-- 2. Customer Table (KhachHang)
CREATE TABLE KhachHang (
    IDKhachHang VARCHAR(10) PRIMARY KEY,
    Ten NVARCHAR(50) NOT NULL,
    SoDienThoai VARCHAR(15) NOT NULL,
    NgayTao DATETIME DEFAULT GETDATE(),
    NgaySua DATETIME DEFAULT GETDATE(),
    Email VARCHAR(100),
    GioiTinh INT CHECK (GioiTinh IN (0, 1)), -- 1: Male, 0: Female
    TrangThai INT DEFAULT 1 -- 1: Active, 0: Inactive
);

-- 3. Product Table (SanPham)
CREATE TABLE SanPham (
    SoThuTu INT IDENTITY(1,1) PRIMARY KEY,
    IDSanPham VARCHAR(10) NOT NULL UNIQUE,
    TenSanPham NVARCHAR(100) NOT NULL,
    NgayTao DATETIME DEFAULT GETDATE(),
    NgaySua DATETIME DEFAULT GETDATE(),
    TrangThai INT DEFAULT 1 -- 1: Active, 0: Inactive
);

-- 4. Voucher Table
CREATE TABLE Voucher (
    IDVoucher VARCHAR(10) PRIMARY KEY,
    Ten NVARCHAR(50) NOT NULL,
    PhanTramGiam INT CHECK (PhanTramGiam >= 0 AND PhanTramGiam <= 100),
    DonHangToiThieu INT DEFAULT 0,
    NgayBatDau DATETIME NOT NULL,
    NgayKetThuc DATETIME NOT NULL,
    GiaTriGiamToiDa INT DEFAULT 0,
    TrangThai INT DEFAULT 1 -- 1: Active, 0: Inactive
);

-- 5. Promotion Table (KhuyenMai)
CREATE TABLE KhuyenMai (
    SoThuTu INT IDENTITY(1,1) PRIMARY KEY,
    IDKhuyenMai VARCHAR(10) NOT NULL UNIQUE,
    TenKhuyenMai NVARCHAR(50),
    NgayBatDau DATETIME NOT NULL,
    NgayKetThuc DATETIME NOT NULL,
    PhanTramGiam INT CHECK (PhanTramGiam >= 0 AND PhanTramGiam <= 100),
    TrangThai INT DEFAULT 1 -- 1: Active, 0: Inactive
);

-- 6. Product Attribute Tables
-- Color Table (MauSac)
CREATE TABLE MauSac (
    IDMauSac VARCHAR(10) PRIMARY KEY,
    Ten NVARCHAR(50) NOT NULL,
    TrangThai INT DEFAULT 1
);

-- Size Table (KichThuoc)
CREATE TABLE KichThuoc (
    IDKichThuoc VARCHAR(10) PRIMARY KEY,
    Ten NVARCHAR(10) NOT NULL,
    TrangThai INT DEFAULT 1
);

-- Material Table (ChatLieu)
CREATE TABLE ChatLieu (
    IDChatLieu VARCHAR(10) PRIMARY KEY,
    Ten NVARCHAR(50) NOT NULL,
    TrangThai INT DEFAULT 1
);

-- Sole Table (DeGiay)
CREATE TABLE DeGiay (
    IDDeGiay VARCHAR(10) PRIMARY KEY,
    Ten NVARCHAR(50) NOT NULL,
    TrangThai INT DEFAULT 1
);

-- Lace Type Table (LoaiDay)
CREATE TABLE LoaiDay (
    IDLoaiDay VARCHAR(10) PRIMARY KEY,
    Ten NVARCHAR(50) NOT NULL,
    TrangThai INT DEFAULT 1
);

-- 7. Product Detail Table (SanPhamChiTiet)
CREATE TABLE SanPhamChiTiet (
    IDSanPhamChiTiet VARCHAR(15) PRIMARY KEY,
    IDSanPham VARCHAR(10) NOT NULL,
    IDKhuyenMai VARCHAR(10),
    IDMauSac VARCHAR(10) NOT NULL,
    IDKichThuoc VARCHAR(10) NOT NULL,
    IDChatLieu VARCHAR(10) NOT NULL,
    IDDeGiay VARCHAR(10) NOT NULL,
    IDLoaiDay VARCHAR(10) NOT NULL,
    TheLoai INT CHECK (TheLoai IN (0, 1)), -- 1: Male, 0: Female
    DonGia INT NOT NULL CHECK (DonGia > 0),
    SoLuong INT DEFAULT 0 CHECK (SoLuong >= 0),
    NgayTao DATETIME DEFAULT GETDATE(),
    NgaySua DATETIME DEFAULT GETDATE(),
    TrangThai INT DEFAULT 1,
    
    FOREIGN KEY (IDSanPham) REFERENCES SanPham(IDSanPham),
    FOREIGN KEY (IDKhuyenMai) REFERENCES KhuyenMai(IDKhuyenMai),
    FOREIGN KEY (IDMauSac) REFERENCES MauSac(IDMauSac),
    FOREIGN KEY (IDKichThuoc) REFERENCES KichThuoc(IDKichThuoc),
    FOREIGN KEY (IDChatLieu) REFERENCES ChatLieu(IDChatLieu),
    FOREIGN KEY (IDDeGiay) REFERENCES DeGiay(IDDeGiay),
    FOREIGN KEY (IDLoaiDay) REFERENCES LoaiDay(IDLoaiDay)
);

-- 8. Invoice Table (HoaDon)
CREATE TABLE HoaDon (
    SoThuTu INT IDENTITY(1,1) PRIMARY KEY,
    IDHoaDon VARCHAR(15) NOT NULL UNIQUE,
    IDNhanVien VARCHAR(10) NOT NULL,
    IDKhachHang VARCHAR(10) NOT NULL,
    IDVoucher VARCHAR(10),
    NgayMuaHang DATETIME DEFAULT GETDATE(),
    TongTien INT DEFAULT 0,
    NgayTao DATETIME DEFAULT GETDATE(),
    NgaySua DATETIME DEFAULT GETDATE(),
    TrangThai INT DEFAULT 0, -- 0: Pending, 1: Paid, 2: Cancelled
    
    FOREIGN KEY (IDNhanVien) REFERENCES NhanVien(IDNhanVien),
    FOREIGN KEY (IDKhachHang) REFERENCES KhachHang(IDKhachHang),
    FOREIGN KEY (IDVoucher) REFERENCES Voucher(IDVoucher)
);

-- 9. Invoice Detail Table (HoaDonChiTiet)
CREATE TABLE HoaDonChiTiet (
    SoThuTu INT IDENTITY(1,1) PRIMARY KEY,
    IDSanPhamChiTiet VARCHAR(15) NOT NULL,
    IDHoaDon VARCHAR(15) NOT NULL,
    SoLuong INT NOT NULL CHECK (SoLuong > 0),
    DonGia INT NOT NULL CHECK (DonGia > 0),
    NgayTao DATETIME DEFAULT GETDATE(),
    TrangThai INT DEFAULT 1,
    
    FOREIGN KEY (IDSanPhamChiTiet) REFERENCES SanPhamChiTiet(IDSanPhamChiTiet),
    FOREIGN KEY (IDHoaDon) REFERENCES HoaDon(IDHoaDon)
);

-- =============================================
-- CREATE INDEXES FOR PERFORMANCE
-- =============================================

CREATE INDEX IX_NhanVien_TenDangNhap ON NhanVien(TenDangNhap);
CREATE INDEX IX_KhachHang_SoDienThoai ON KhachHang(SoDienThoai);
CREATE INDEX IX_SanPhamChiTiet_IDSanPham ON SanPhamChiTiet(IDSanPham);
CREATE INDEX IX_HoaDon_NgayMuaHang ON HoaDon(NgayMuaHang);
CREATE INDEX IX_HoaDon_TrangThai ON HoaDon(TrangThai);

-- =============================================
-- INSERT SAMPLE DATA
-- =============================================

-- Insert Colors
INSERT INTO MauSac (IDMauSac, Ten) VALUES
('MS001', N'Đen'),
('MS002', N'Trắng'),
('MS003', N'Đỏ'),
('MS004', N'Xanh'),
('MS005', N'Nâu');

-- Insert Sizes
INSERT INTO KichThuoc (IDKichThuoc, Ten) VALUES
('KT001', '36'),
('KT002', '37'),
('KT003', '38'),
('KT004', '39'),
('KT005', '40'),
('KT006', '41'),
('KT007', '42'),
('KT008', '43');

-- Insert Materials
INSERT INTO ChatLieu (IDChatLieu, Ten) VALUES
('CL001', N'Da thật'),
('CL002', N'Da tổng hợp'),
('CL003', N'Vải canvas'),
('CL004', N'Lưới thoáng khí'),
('CL005', N'Da lộn');

-- Insert Sole Types
INSERT INTO DeGiay (IDDeGiay, Ten) VALUES
('DG001', N'Đế cao su'),
('DG002', N'Đế eva'),
('DG003', N'Đế pu'),
('DG004', N'Đế da'),
('DG005', N'Đế composite');

-- Insert Lace Types
INSERT INTO LoaiDay (IDLoaiDay, Ten) VALUES
('LD001', N'Dây tròn'),
('LD002', N'Dây dẹt'),
('LD003', N'Dây da'),
('LD004', N'Không dây'),
('LD005', N'Dây thun');

-- Insert Employees
INSERT INTO NhanVien (IDNhanVien, Ten, Tuoi, GioiTinh, DiaChi, VaiTro, TenDangNhap, MatKhau) VALUES
('NV001', N'Nguyễn Văn An', 25, 1, N'Hà Nội', 1, 'admin', '123456'),
('NV002', N'Trần Thị Bình', 23, 0, N'Hồ Chí Minh', 0, 'nhanvien1', '123456'),
('NV003', N'Lê Văn Cường', 27, 1, N'Đà Nẵng', 0, 'nhanvien2', '123456');

-- Insert Customers
INSERT INTO KhachHang (IDKhachHang, Ten, SoDienThoai, Email, GioiTinh) VALUES
('KH001', N'Phạm Văn Dũng', '0912345678', 'dung@email.com', 1),
('KH002', N'Hoàng Thị Hoa', '0987654321', 'hoa@email.com', 0),
('KH003', N'Vũ Minh Khang', '0909123456', 'khang@email.com', 1);

-- Insert Products
INSERT INTO SanPham (IDSanPham, TenSanPham) VALUES
('SP001', N'Giày thể thao Nike'),
('SP002', N'Giày da công sở'),
('SP003', N'Giày sneaker Adidas'),
('SP004', N'Giày cao gót nữ'),
('SP005', N'Giày sandal nữ');

-- Insert Promotions
INSERT INTO KhuyenMai (IDKhuyenMai, TenKhuyenMai, NgayBatDau, NgayKetThuc, PhanTramGiam) VALUES
('KM001', N'Giảm giá mùa hè', '2024-06-01', '2024-08-31', 15),
('KM002', N'Khuyến mãi Black Friday', '2024-11-24', '2024-11-30', 30);

-- Insert Vouchers
INSERT INTO Voucher (IDVoucher, Ten, PhanTramGiam, DonHangToiThieu, NgayBatDau, NgayKetThuc, GiaTriGiamToiDa) VALUES
('VC001', N'Voucher 10%', 10, 500000, '2024-01-01', '2024-12-31', 100000),
('VC002', N'Voucher VIP 20%', 20, 1000000, '2024-01-01', '2024-12-31', 200000);

-- Insert Product Details
INSERT INTO SanPhamChiTiet (IDSanPhamChiTiet, IDSanPham, IDKhuyenMai, IDMauSac, IDKichThuoc, IDChatLieu, IDDeGiay, IDLoaiDay, TheLoai, DonGia, SoLuong) VALUES
('SPCT001', 'SP001', 'KM001', 'MS001', 'KT005', 'CL004', 'DG002', 'LD001', 1, 1200000, 50),
('SPCT002', 'SP001', NULL, 'MS002', 'KT006', 'CL004', 'DG002', 'LD001', 1, 1200000, 30),
('SPCT003', 'SP002', NULL, 'MS001', 'KT007', 'CL001', 'DG004', 'LD003', 1, 2500000, 20),
('SPCT004', 'SP004', 'KM002', 'MS003', 'KT002', 'CL001', 'DG001', 'LD004', 0, 1800000, 25),
('SPCT005', 'SP005', NULL, 'MS002', 'KT003', 'CL002', 'DG001', 'LD002', 0, 800000, 40);

-- =============================================
-- USEFUL QUERIES
-- =============================================

-- 1. Get all products with details
SELECT 
    spct.IDSanPhamChiTiet,
    sp.TenSanPham,
    CASE WHEN spct.TheLoai = 1 THEN N'Nam' ELSE N'Nữ' END AS TheLoai,
    spct.DonGia,
    spct.SoLuong,
    ms.Ten AS MauSac,
    kt.Ten AS KichThuoc,
    cl.Ten AS ChatLieu,
    dg.Ten AS DeGiay,
    ld.Ten AS LoaiDay,
    CASE WHEN spct.TrangThai = 1 THEN N'Hoạt động' ELSE N'Không hoạt động' END AS TrangThai
FROM SanPhamChiTiet spct
    JOIN SanPham sp ON sp.IDSanPham = spct.IDSanPham
    JOIN MauSac ms ON ms.IDMauSac = spct.IDMauSac
    JOIN KichThuoc kt ON kt.IDKichThuoc = spct.IDKichThuoc
    JOIN ChatLieu cl ON cl.IDChatLieu = spct.IDChatLieu
    JOIN DeGiay dg ON dg.IDDeGiay = spct.IDDeGiay
    JOIN LoaiDay ld ON ld.IDLoaiDay = spct.IDLoaiDay;

-- 2. Get invoice with customer and employee info
SELECT 
    hd.IDHoaDon,
    nv.Ten AS TenNhanVien,
    kh.Ten AS TenKhachHang,
    kh.SoDienThoai,
    hd.NgayMuaHang,
    hd.TongTien,
    CASE 
        WHEN hd.TrangThai = 0 THEN N'Chưa thanh toán'
        WHEN hd.TrangThai = 1 THEN N'Đã thanh toán'
        ELSE N'Đã hủy'
    END AS TrangThai
FROM HoaDon hd
    JOIN NhanVien nv ON nv.IDNhanVien = hd.IDNhanVien
    JOIN KhachHang kh ON kh.IDKhachHang = hd.IDKhachHang;

-- 3. Sales report by month
SELECT 
    YEAR(NgayMuaHang) AS Nam,
    MONTH(NgayMuaHang) AS Thang,
    COUNT(*) AS SoHoaDon,
    SUM(TongTien) AS DoanhThu
FROM HoaDon 
WHERE TrangThai = 1  -- Only paid invoices
GROUP BY YEAR(NgayMuaHang), MONTH(NgayMuaHang)
ORDER BY Nam DESC, Thang DESC;

-- 4. Top selling products
SELECT 
    sp.TenSanPham,
    SUM(hdct.SoLuong) AS SoLuongBan,
    SUM(hdct.SoLuong * hdct.DonGia) AS DoanhThu
FROM HoaDonChiTiet hdct
    JOIN SanPhamChiTiet spct ON spct.IDSanPhamChiTiet = hdct.IDSanPhamChiTiet
    JOIN SanPham sp ON sp.IDSanPham = spct.IDSanPham
    JOIN HoaDon hd ON hd.IDHoaDon = hdct.IDHoaDon
WHERE hd.TrangThai = 1
GROUP BY sp.IDSanPham, sp.TenSanPham
ORDER BY SoLuongBan DESC;

-- 5. Low stock alert
SELECT 
    spct.IDSanPhamChiTiet,
    sp.TenSanPham,
    ms.Ten AS MauSac,
    kt.Ten AS KichThuoc,
    spct.SoLuong
FROM SanPhamChiTiet spct
    JOIN SanPham sp ON sp.IDSanPham = spct.IDSanPham
    JOIN MauSac ms ON ms.IDMauSac = spct.IDMauSac
    JOIN KichThuoc kt ON kt.IDKichThuoc = spct.IDKichThuoc
WHERE spct.SoLuong < 10 AND spct.TrangThai = 1
ORDER BY spct.SoLuong ASC;

-- 6. Customer purchase history
SELECT 
    kh.Ten AS TenKhachHang,
    hd.IDHoaDon,
    hd.NgayMuaHang,
    hd.TongTien,
    COUNT(hdct.SoThuTu) AS SoSanPham
FROM KhachHang kh
    JOIN HoaDon hd ON hd.IDKhachHang = kh.IDKhachHang
    LEFT JOIN HoaDonChiTiet hdct ON hdct.IDHoaDon = hd.IDHoaDon
WHERE hd.TrangThai = 1
GROUP BY kh.IDKhachHang, kh.Ten, hd.IDHoaDon, hd.NgayMuaHang, hd.TongTien
ORDER BY hd.NgayMuaHang DESC;

-- =============================================
-- STORED PROCEDURES
-- =============================================

-- Procedure to add new invoice
GO
CREATE PROCEDURE sp_ThemHoaDon
    @IDHoaDon VARCHAR(15),
    @IDNhanVien VARCHAR(10),
    @IDKhachHang VARCHAR(10),
    @IDVoucher VARCHAR(10) = NULL
AS
BEGIN
    INSERT INTO HoaDon (IDHoaDon, IDNhanVien, IDKhachHang, IDVoucher, TrangThai)
    VALUES (@IDHoaDon, @IDNhanVien, @IDKhachHang, @IDVoucher, 0);
END;
GO

-- Procedure to update product stock
CREATE PROCEDURE sp_CapNhatTonKho
    @IDSanPhamChiTiet VARCHAR(15),
    @SoLuongMoi INT
AS
BEGIN
    UPDATE SanPhamChiTiet 
    SET SoLuong = @SoLuongMoi, NgaySua = GETDATE()
    WHERE IDSanPhamChiTiet = @IDSanPhamChiTiet;
END;
GO

-- Function to calculate discounted price
CREATE FUNCTION fn_TinhGiaKhuyenMai(@IDSanPhamChiTiet VARCHAR(15))
RETURNS INT
AS
BEGIN
    DECLARE @GiaBan INT, @PhanTramGiam INT, @GiaSauGiam INT;
    
    SELECT 
        @GiaBan = spct.DonGia,
        @PhanTramGiam = ISNULL(km.PhanTramGiam, 0)
    FROM SanPhamChiTiet spct
        LEFT JOIN KhuyenMai km ON km.IDKhuyenMai = spct.IDKhuyenMai 
            AND GETDATE() BETWEEN km.NgayBatDau AND km.NgayKetThuc
            AND km.TrangThai = 1
    WHERE spct.IDSanPhamChiTiet = @IDSanPhamChiTiet;
    
    SET @GiaSauGiam = @GiaBan - (@GiaBan * @PhanTramGiam / 100);
    
    RETURN @GiaSauGiam;
END;
GO

PRINT 'Database DA_final created successfully!'; 