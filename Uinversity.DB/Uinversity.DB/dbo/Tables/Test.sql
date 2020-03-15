CREATE TABLE [dbo].[Test] (
    [Id]                 UNIQUEIDENTIFIER CONSTRAINT [DF_Test_Id] DEFAULT (newid()) NOT NULL,
    [Name]               NVARCHAR (50)    NOT NULL,
    [SubjectId]          UNIQUEIDENTIFIER NOT NULL,
    [QuestionCount]      INT              NOT NULL,
    [QuestionVariations] INT              NOT NULL,
    CONSTRAINT [PK_Test] PRIMARY KEY CLUSTERED ([Id] ASC),
    CONSTRAINT [FK_Test_Subject] FOREIGN KEY ([SubjectId]) REFERENCES [dbo].[Subject] ([Id])
);

