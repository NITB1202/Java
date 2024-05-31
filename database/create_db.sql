CREATE DATABASE libManager;
GO
    USE [libManager]
GO

    /****** Object:  Table [dbo].[Author]    Script Date: 10/22/2023 6:15:33 PM ******/
SET
    ANSI_NULLS ON
GO
SET
    QUOTED_IDENTIFIER ON
GO
    CREATE TABLE [dbo].[author](
        [id] [int] IDENTITY(1, 1) NOT NULL,
        [name] [nvarchar](30) NOT NULL,
        [isActive] [bit] NOT NULL,
        PRIMARY KEY CLUSTERED ([id] ASC) WITH (
            PAD_INDEX = OFF,
            STATISTICS_NORECOMPUTE = OFF,
            IGNORE_DUP_KEY = OFF,
            ALLOW_ROW_LOCKS = ON,
            ALLOW_PAGE_LOCKS = ON,
            OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF
        ) ON [PRIMARY]
    ) ON [PRIMARY]
GO
    /****** Object:  Table [dbo].[Book]    Script Date: 10/22/2023 6:15:33 PM ******/
SET
    ANSI_NULLS ON
GO
SET
    QUOTED_IDENTIFIER ON
GO
    CREATE TABLE [dbo].[book](
        [id] [int] IDENTITY(1, 1) NOT NULL,
        [name] [nvarchar](200) NULL,
        [isActive] [bit] NOT NULL,
        PRIMARY KEY CLUSTERED ([id] ASC) WITH (
            PAD_INDEX = OFF,
            STATISTICS_NORECOMPUTE = OFF,
            IGNORE_DUP_KEY = OFF,
            ALLOW_ROW_LOCKS = ON,
            ALLOW_PAGE_LOCKS = ON,
            OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF
        ) ON [PRIMARY]
    ) ON [PRIMARY]
GO
    /****** Object:  Table [dbo].[Category]    Script Date: 10/22/2023 6:15:33 PM ******/
SET
    ANSI_NULLS ON
GO
SET
    QUOTED_IDENTIFIER ON
GO
    CREATE TABLE [dbo].[category](
        [id] [int] IDENTITY(1, 1) NOT NULL,
        [name] [nvarchar](30) NOT NULL,
        [isActive] [bit] NOT NULL,
        PRIMARY KEY CLUSTERED ([id] ASC) WITH (
            PAD_INDEX = OFF,
            STATISTICS_NORECOMPUTE = OFF,
            IGNORE_DUP_KEY = OFF,
            ALLOW_ROW_LOCKS = ON,
            ALLOW_PAGE_LOCKS = ON,
            OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF
        ) ON [PRIMARY]
    ) ON [PRIMARY]
GO
    /****** Object:  Table [dbo].[Publisher]    Script Date: 10/22/2023 6:15:33 PM ******/
SET
    ANSI_NULLS ON
GO
SET
    QUOTED_IDENTIFIER ON
GO
    CREATE TABLE [dbo].[publisher](
        [id] [int] IDENTITY(1, 1) NOT NULL,
        [name] [nvarchar](30) NOT NULL,
        [isActive] [bit] NOT NULL,
        PRIMARY KEY CLUSTERED ([id] ASC) WITH (
            PAD_INDEX = OFF,
            STATISTICS_NORECOMPUTE = OFF,
            IGNORE_DUP_KEY = OFF,
            ALLOW_ROW_LOCKS = ON,
            ALLOW_PAGE_LOCKS = ON,
            OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF
        ) ON [PRIMARY]
    ) ON [PRIMARY]
GO
    /****** Object:  Table [dbo].[Supplier]    Script Date: 10/22/2023 6:15:33 PM ******/
SET
    ANSI_NULLS ON
GO
SET
    QUOTED_IDENTIFIER ON
GO
    CREATE TABLE [dbo].[supplier](
        [id] [int] IDENTITY(1, 1) NOT NULL,
        [name] [nvarchar](30) NOT NULL,
        [isActive] [bit] NOT NULL,
        PRIMARY KEY CLUSTERED ([id] ASC) WITH (
            PAD_INDEX = OFF,
            STATISTICS_NORECOMPUTE = OFF,
            IGNORE_DUP_KEY = OFF,
            ALLOW_ROW_LOCKS = ON,
            ALLOW_PAGE_LOCKS = ON,
            OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF
        ) ON [PRIMARY]
    ) ON [PRIMARY]
GO
SET
    ANSI_NULLS ON
GO
SET
    QUOTED_IDENTIFIER ON
GO
    CREATE TABLE [dbo].[permissions](
        [id] [int] IDENTITY(1, 1) NOT NULL,
        [name] [nvarchar](30) NOT NULL,
        [isActive] [bit] NOT NULL PRIMARY KEY CLUSTERED ([id] ASC) WITH (
            PAD_INDEX = OFF,
            STATISTICS_NORECOMPUTE = OFF,
            IGNORE_DUP_KEY = OFF,
            ALLOW_ROW_LOCKS = ON,
            ALLOW_PAGE_LOCKS = ON,
            OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF
        ) ON [PRIMARY]
    ) ON [PRIMARY]
GO
    /****** Object:  Table [dbo].[Roles]    Script Date: 10/22/2023 6:15:33 PM ******/
SET
    ANSI_NULLS ON
GO
SET
    QUOTED_IDENTIFIER ON
GO
    CREATE TABLE [dbo].[roles](
        [id] [varchar](2) NOT NULL,
        [name] [nvarchar](30) NOT NULL,
        [isActive] [bit] NOT NULL,
        PRIMARY KEY CLUSTERED ([id] ASC) WITH (
            PAD_INDEX = OFF,
            STATISTICS_NORECOMPUTE = OFF,
            IGNORE_DUP_KEY = OFF,
            ALLOW_ROW_LOCKS = ON,
            ALLOW_PAGE_LOCKS = ON,
            OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF
        ) ON [PRIMARY]
    ) ON [PRIMARY]
GO
    /****** Object:  Table [dbo].[rolePermissions]    Script Date: 10/22/2023 6:15:33 PM ******/
SET
    ANSI_NULLS ON
GO
SET
    QUOTED_IDENTIFIER ON
GO
    CREATE TABLE [dbo].[role_permissions](
        [positionID] [varchar](2) NOT NULL,
        [authorityID] [int] NOT NULL,
        [Per_access] bit NOT NULL,
        [Per_create] bit NOT NULL,
        [Per_view] bit NOT NULL,
        [Per_edit] bit NOT NULL,
        [Per_delete] bit NOT NULL,
        [IsActive] bit,
        PRIMARY KEY CLUSTERED ([positionID] ASC, [authorityID] ASC) WITH (
            PAD_INDEX = OFF,
            STATISTICS_NORECOMPUTE = OFF,
            IGNORE_DUP_KEY = OFF,
            ALLOW_ROW_LOCKS = ON,
            ALLOW_PAGE_LOCKS = ON,
            OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF
        ) ON [PRIMARY]
    ) ON [PRIMARY]
GO
    /****** Object:  Table [dbo].[Staff]    Script Date: 10/22/2023 6:15:33 PM ******/
SET
    ANSI_NULLS ON
GO
SET
    QUOTED_IDENTIFIER ON
GO
    CREATE TABLE [dbo].[staff](
        [id] [int] IDENTITY(1000, 1) NOT NULL,
        [managerID] [int] NULL,
        [name] [nvarchar](30) NOT NULL,
        [tel] [varchar](10) NOT NULL,
        [address] [nvarchar](50) NOT NULL,
        [isActive] [bit] NOT NULL,
        PRIMARY KEY CLUSTERED ([id] ASC) WITH (
            PAD_INDEX = OFF,
            STATISTICS_NORECOMPUTE = OFF,
            IGNORE_DUP_KEY = OFF,
            ALLOW_ROW_LOCKS = ON,
            ALLOW_PAGE_LOCKS = ON,
            OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF
        ) ON [PRIMARY]
    ) ON [PRIMARY]
GO
SET
    QUOTED_IDENTIFIER ON
GO
    CREATE TABLE [dbo].[account](
        [userID] [int] NOT NULL,
        [userName] [varchar](20) NOT NULL,
        [password] [varchar](200) NOT NULL,
        [positionID] [varchar](2) NOT NULL,
        [dayCreate] [date] NOT NULL,
        [isActive] [bit] NOT NULL,
        PRIMARY KEY CLUSTERED ([userID] ASC) WITH (
            PAD_INDEX = OFF,
            STATISTICS_NORECOMPUTE = OFF,
            IGNORE_DUP_KEY = OFF,
            ALLOW_ROW_LOCKS = ON,
            ALLOW_PAGE_LOCKS = ON,
            OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF
        ) ON [PRIMARY]
    ) ON [PRIMARY]
GO
    /****** Object:  Table [dbo].[Reader]    Script Date: 10/22/2023 6:15:33 PM ******/
SET
    ANSI_NULLS ON
GO
SET
    QUOTED_IDENTIFIER ON
GO
    CREATE TABLE [dbo].[reader](
        [id] [int] IDENTITY(1000, 1) NOT NULL,
        [name] [nvarchar](30) NOT NULL,
        [tel] [varchar](10) NOT NULL,
        [address] [nvarchar](50) NULL,
        [fineDate] [date] NULL,
        [isActive] [bit] NOT NULL,
        PRIMARY KEY CLUSTERED ([id] ASC) WITH (
            PAD_INDEX = OFF,
            STATISTICS_NORECOMPUTE = OFF,
            IGNORE_DUP_KEY = OFF,
            ALLOW_ROW_LOCKS = ON,
            ALLOW_PAGE_LOCKS = ON,
            OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF
        ) ON [PRIMARY]
    ) ON [PRIMARY]
GO
    /****** Object:  Table [dbo].[borrowCard]    Script Date: 10/22/2023 6:15:33 PM ******/
SET
    ANSI_NULLS ON
GO
SET
    QUOTED_IDENTIFIER ON
GO
    CREATE TABLE [dbo].[borrow_card](
        [id] [int] IDENTITY(1, 1) NOT NULL,
        [startDate] [date] NOT NULL,
        [expReDate] [date] NULL,
        [realReDate] [date] NULL,
        [deposit] [bigint] NOT NULL,
        [isActive] [bit] NULL,
        [readerID] [int] NOT NULL,
        [staffID] [int] NOT NULL,
        PRIMARY KEY CLUSTERED ([id] ASC) WITH (
            PAD_INDEX = OFF,
            STATISTICS_NORECOMPUTE = OFF,
            IGNORE_DUP_KEY = OFF,
            ALLOW_ROW_LOCKS = ON,
            ALLOW_PAGE_LOCKS = ON,
            OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF
        ) ON [PRIMARY]
    ) ON [PRIMARY]
GO
    /****** Object:  Table [dbo].[cpBook]    Script Date: 10/22/2023 6:15:33 PM ******/
SET
    ANSI_NULLS ON
GO
SET
    QUOTED_IDENTIFIER ON
GO
    CREATE TABLE [dbo].[cp_book](
        [ISBN] [varchar](17) NOT NULL,
        [bookID] [int] NOT NULL,
        [borrowNum] [int] NOT NULL,
        [storeNum] [int] NOT NULL,
        [edition] [nvarchar](50) NULL,
        [publisherID] [int] NOT NULL,
        [isActive] [bit] NOT NULL,
        [Cost] [bigint] NULL,
        [img] [varchar](100) NULL,
        PRIMARY KEY CLUSTERED ([ISBN] ASC) WITH (
            PAD_INDEX = OFF,
            STATISTICS_NORECOMPUTE = OFF,
            IGNORE_DUP_KEY = OFF,
            ALLOW_ROW_LOCKS = ON,
            ALLOW_PAGE_LOCKS = ON,
            OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF
        ) ON [PRIMARY]
    ) ON [PRIMARY]
GO
    /****** Object:  Table [dbo].[bookAuthor]    Script Date: 10/22/2023 6:15:33 PM ******/
SET
    ANSI_NULLS ON
GO
SET
    QUOTED_IDENTIFIER ON
GO
    CREATE TABLE [dbo].[book_author](
        [authorID] [int] NOT NULL,
        [ISBN] [varchar](17) NOT NULL,
        PRIMARY KEY CLUSTERED ([authorID] ASC, [ISBN] ASC) WITH (
            PAD_INDEX = OFF,
            STATISTICS_NORECOMPUTE = OFF,
            IGNORE_DUP_KEY = OFF,
            ALLOW_ROW_LOCKS = ON,
            ALLOW_PAGE_LOCKS = ON,
            OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF
        ) ON [PRIMARY]
    ) ON [PRIMARY]
GO
    /****** Object:  Table [dbo].[bookCategory]    Script Date: 10/22/2023 6:15:33 PM ******/
SET
    ANSI_NULLS ON
GO
SET
    QUOTED_IDENTIFIER ON
GO
    CREATE TABLE [dbo].[book_category](
        [categoryID] [int] NOT NULL,
        [ISBN] [varchar](17) NOT NULL,
        PRIMARY KEY CLUSTERED ([categoryID] ASC, [ISBN] ASC) WITH (
            PAD_INDEX = OFF,
            STATISTICS_NORECOMPUTE = OFF,
            IGNORE_DUP_KEY = OFF,
            ALLOW_ROW_LOCKS = ON,
            ALLOW_PAGE_LOCKS = ON,
            OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF
        ) ON [PRIMARY]
    ) ON [PRIMARY]
GO
    /****** Object:  Table [dbo].[supplyCard]    Script Date: 10/22/2023 6:15:33 PM ******/
SET
    ANSI_NULLS ON
GO
SET
    QUOTED_IDENTIFIER ON
GO
    CREATE TABLE [dbo].[supply_card](
        [id] [int] IDENTITY(1, 1) NOT NULL,
        [supDate] [date] NOT NULL,
        [providerID] int NOT NULL,
        [feePaid] [int],
        [staffID] [int] NOT NULL,
        PRIMARY KEY CLUSTERED ([id] ASC) WITH (
            PAD_INDEX = OFF,
            STATISTICS_NORECOMPUTE = OFF,
            IGNORE_DUP_KEY = OFF,
            ALLOW_ROW_LOCKS = ON,
            ALLOW_PAGE_LOCKS = ON,
            OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF
        ) ON [PRIMARY]
    ) ON [PRIMARY]
GO
    /****** Object:  Table [dbo].[detailBC]    Script Date: 10/22/2023 6:15:33 PM ******/
SET
    ANSI_NULLS ON
GO
SET
    QUOTED_IDENTIFIER ON
GO
    CREATE TABLE [dbo].[detail_borrow_card](
        [bcID] [int] NOT NULL,
        [ISBN] [varchar](17) NOT NULL,
        [num] [int] NOT NULL,
        [description] [nvarchar](200) NULL,
        [lost] [int] NULL,
        PRIMARY KEY CLUSTERED ([bcID] ASC, [ISBN] ASC) WITH (
            PAD_INDEX = OFF,
            STATISTICS_NORECOMPUTE = OFF,
            IGNORE_DUP_KEY = OFF,
            ALLOW_ROW_LOCKS = ON,
            ALLOW_PAGE_LOCKS = ON,
            OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF
        ) ON [PRIMARY]
    ) ON [PRIMARY]
GO
    /****** Object:  Table [dbo].[detailSC]    Script Date: 10/22/2023 6:15:33 PM ******/
SET
    ANSI_NULLS ON
GO
SET
    QUOTED_IDENTIFIER ON
GO
    CREATE TABLE [dbo].[detail_supply_card](
        [scID] [int] NOT NULL,
        [ISBN] [varchar](17) NOT NULL,
        [num] [int] NOT NULL,
        PRIMARY KEY CLUSTERED ([scID] ASC, [ISBN] ASC) WITH (
            PAD_INDEX = OFF,
            STATISTICS_NORECOMPUTE = OFF,
            IGNORE_DUP_KEY = OFF,
            ALLOW_ROW_LOCKS = ON,
            ALLOW_PAGE_LOCKS = ON,
            OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF
        ) ON [PRIMARY]
    ) ON [PRIMARY]
GO
ALTER TABLE
    [dbo].[account]
ADD
    DEFAULT ((1)) FOR [isActive]
GO
ALTER TABLE
    [dbo].[author]
ADD
    DEFAULT ((1)) FOR [isActive]
GO
ALTER TABLE
    [dbo].[book]
ADD
    DEFAULT ((1)) FOR [isActive]
GO
ALTER TABLE
    [dbo].[borrow_card]
ADD
    DEFAULT ((1)) FOR [isActive]
GO
ALTER TABLE
    [dbo].[category]
ADD
    DEFAULT ((1)) FOR [isActive]
GO
ALTER TABLE
    [dbo].[cp_book]
ADD
    DEFAULT ((1)) FOR [isActive]
GO
ALTER TABLE
    [dbo].[detail_borrow_card]
ADD
    DEFAULT ((0)) FOR [lost]
GO
ALTER TABLE
    [dbo].[publisher]
ADD
    DEFAULT ((1)) FOR [isActive]
GO
ALTER TABLE
    [dbo].[reader]
ADD
    DEFAULT ((1)) FOR [isActive]
GO
ALTER TABLE
    [dbo].[staff]
ADD
    DEFAULT ((1)) FOR [isActive]
GO
ALTER TABLE
    [dbo].[permissions]
ADD
    DEFAULT ((1)) FOR [isActive]
GO
ALTER TABLE
    [dbo].[supplier]
ADD
    DEFAULT ((1)) FOR [isActive]
GO
ALTER TABLE
    [dbo].[account] WITH CHECK
ADD
    FOREIGN KEY([positionID]) REFERENCES [dbo].[roles] ([id])
GO
ALTER TABLE
    [dbo].[account] WITH CHECK
ADD
    FOREIGN KEY([userID]) REFERENCES [dbo].[staff] ([id])
GO
ALTER TABLE
    [dbo].[book_author] WITH CHECK
ADD
    FOREIGN KEY([authorID]) REFERENCES [dbo].[author] ([id])
GO
ALTER TABLE
    [dbo].[book_author] WITH CHECK
ADD
    FOREIGN KEY([ISBN]) REFERENCES [dbo].[cp_book] ([ISBN])
GO
ALTER TABLE
    [dbo].[book_category] WITH CHECK
ADD
    FOREIGN KEY([categoryID]) REFERENCES [dbo].[category] ([id])
GO
ALTER TABLE
    [dbo].[book_category] WITH CHECK
ADD
    FOREIGN KEY([ISBN]) REFERENCES [dbo].[cp_book] ([ISBN])
GO
ALTER TABLE
    [dbo].[borrow_card] WITH CHECK
ADD
    FOREIGN KEY([readerID]) REFERENCES [dbo].[reader] ([id])
GO
ALTER TABLE
    [dbo].[borrow_card] WITH CHECK
ADD
    FOREIGN KEY([staffID]) REFERENCES [dbo].[staff] ([id])
GO
ALTER TABLE
    [dbo].[cp_book] WITH CHECK
ADD
    FOREIGN KEY([bookID]) REFERENCES [dbo].[book] ([id])
GO
ALTER TABLE
    [dbo].[cp_book] WITH CHECK
ADD
    FOREIGN KEY([publisherID]) REFERENCES [dbo].[publisher] ([id])
GO
ALTER TABLE
    [dbo].[detail_borrow_card] WITH CHECK
ADD
    FOREIGN KEY([bcID]) REFERENCES [dbo].[borrow_card] ([id])
GO
ALTER TABLE
    [dbo].[detail_borrow_card] WITH CHECK
ADD
    FOREIGN KEY([ISBN]) REFERENCES [dbo].[cp_book] ([ISBN])
GO
ALTER TABLE
    [dbo].[detail_supply_card] WITH CHECK
ADD
    FOREIGN KEY([ISBN]) REFERENCES [dbo].[cp_book] ([ISBN])
GO
ALTER TABLE
    [dbo].[detail_supply_card] WITH CHECK
ADD
    FOREIGN KEY([scID]) REFERENCES [dbo].[supply_card] ([id])
GO
ALTER TABLE
    [dbo].[role_permissions] WITH CHECK
ADD
    FOREIGN KEY([authorityID]) REFERENCES [dbo].[permissions] ([id])
GO
ALTER TABLE
    [dbo].[role_permissions] WITH CHECK
ADD
    FOREIGN KEY([positionID]) REFERENCES [dbo].[roles] ([id])
GO
ALTER TABLE
    [dbo].[staff] WITH CHECK
ADD
    FOREIGN KEY([managerID]) REFERENCES [dbo].[staff] ([id])
GO
ALTER TABLE
    [dbo].[supply_card] WITH CHECK
ADD
    FOREIGN KEY([staffID]) REFERENCES [dbo].[staff] ([id])
GO
ALTER TABLE
    [dbo].[supply_card] WITH CHECK
ADD
    FOREIGN KEY([providerID]) REFERENCES [dbo].[supplier] ([id])
GO