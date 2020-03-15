CREATE TABLE [dbo].[SelectedAnswer] (
    [Id]            UNIQUEIDENTIFIER CONSTRAINT [DF_SelectedAnswer_Id] DEFAULT (newid()) NOT NULL,
    [TestAttemptId] UNIQUEIDENTIFIER NOT NULL,
    [AnswerId]      UNIQUEIDENTIFIER NOT NULL,
    CONSTRAINT [PK_SelectedAnswer] PRIMARY KEY CLUSTERED ([Id] ASC),
    CONSTRAINT [FK_SelectedAnswer_Answer] FOREIGN KEY ([AnswerId]) REFERENCES [dbo].[Answer] ([Id]),
    CONSTRAINT [FK_SelectedAnswer_TestAttempt] FOREIGN KEY ([TestAttemptId]) REFERENCES [dbo].[TestAttempt] ([Id])
);

