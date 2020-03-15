CREATE TABLE [dbo].[Student] (
    [Id]            UNIQUEIDENTIFIER CONSTRAINT [DF_Student_Id] DEFAULT (newid()) NOT NULL,
    [FacultyNumber] NVARCHAR (10)    NOT NULL,
    [FirstName]     NVARCHAR (50)    NOT NULL,
    [LastName]      NVARCHAR (50)    NOT NULL,
    [Email]         NVARCHAR (255)   NULL,
    [ClassId]       UNIQUEIDENTIFIER NOT NULL,
    CONSTRAINT [PK_Student] PRIMARY KEY CLUSTERED ([Id] ASC),
    CONSTRAINT [FK_Student_Class] FOREIGN KEY ([ClassId]) REFERENCES [dbo].[Class] ([Id])
);

