CREATE TABLE [dbo].[Answer] (
    [Id]         UNIQUEIDENTIFIER CONSTRAINT [DF_Answer_Id] DEFAULT (newid()) NOT NULL,
    [Data]       NVARCHAR (255)   NOT NULL,
    [IsCorrect]  BIT              NOT NULL,
    [QuestionId] UNIQUEIDENTIFIER NOT NULL,
    CONSTRAINT [PK_Answer] PRIMARY KEY CLUSTERED ([Id] ASC),
    CONSTRAINT [FK_Answer_Question] FOREIGN KEY ([QuestionId]) REFERENCES [dbo].[Question] ([Id])
);

