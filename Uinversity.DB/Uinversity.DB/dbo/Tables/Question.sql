CREATE TABLE [dbo].[Question] (
    [Id]     UNIQUEIDENTIFIER CONSTRAINT [DF_Question_Id] DEFAULT (newid()) NOT NULL,
    [Data]   NVARCHAR (255)   NOT NULL,
    [TestId] UNIQUEIDENTIFIER NOT NULL,
    CONSTRAINT [PK_Question] PRIMARY KEY CLUSTERED ([Id] ASC),
    CONSTRAINT [FK_Question_Test] FOREIGN KEY ([TestId]) REFERENCES [dbo].[Test] ([Id])
);

