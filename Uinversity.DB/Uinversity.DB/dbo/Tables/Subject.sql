CREATE TABLE [dbo].[Subject] (
    [Id]        UNIQUEIDENTIFIER CONSTRAINT [DF_Subject_Id] DEFAULT (newid()) NOT NULL,
    [Name]      NVARCHAR (255)   NOT NULL,
    [Semester]  INT              NOT NULL,
    [ClassId]   UNIQUEIDENTIFIER NOT NULL,
    [TeacherId] UNIQUEIDENTIFIER NOT NULL,
    CONSTRAINT [PK_Subject] PRIMARY KEY CLUSTERED ([Id] ASC),
    CONSTRAINT [FK_Subject_Class] FOREIGN KEY ([ClassId]) REFERENCES [dbo].[Class] ([Id]),
    CONSTRAINT [FK_Subject_Teacher] FOREIGN KEY ([TeacherId]) REFERENCES [dbo].[Teacher] ([Id])
);

