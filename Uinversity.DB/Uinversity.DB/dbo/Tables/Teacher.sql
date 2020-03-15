CREATE TABLE [dbo].[Teacher] (
    [Id]        UNIQUEIDENTIFIER CONSTRAINT [DF_Teacher_Id] DEFAULT (newid()) NOT NULL,
    [FirstName] NVARCHAR (50)    NOT NULL,
    [LastName]  NVARCHAR (50)    NOT NULL,
    [FacultyId] UNIQUEIDENTIFIER NOT NULL,
    CONSTRAINT [PK_Teacher] PRIMARY KEY CLUSTERED ([Id] ASC),
    CONSTRAINT [FK_Teacher_Faculty] FOREIGN KEY ([FacultyId]) REFERENCES [dbo].[Faculty] ([Id])
);

