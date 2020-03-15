CREATE TABLE [dbo].[TestAttempt] (
    [Id]        UNIQUEIDENTIFIER CONSTRAINT [DF_TestAttempt_Id] DEFAULT (newid()) NOT NULL,
    [StudentId] UNIQUEIDENTIFIER NOT NULL,
    [Date]      DATETIME2 (1)    NOT NULL,
    CONSTRAINT [PK_TestAttempt] PRIMARY KEY CLUSTERED ([Id] ASC),
    CONSTRAINT [FK_TestAttempt_Student] FOREIGN KEY ([StudentId]) REFERENCES [dbo].[Student] ([Id])
);

