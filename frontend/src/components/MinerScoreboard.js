import React from "react";

export const MinerScoreboard = ({totalCpC, totalCoin, totalPrice}) => {
  return <table className="miner-scoreboard">
    <tbody>
    <tr>
      <td>TOTAL CPC</td>
      <td>{totalCpC}</td>
    </tr>
    <tr>
      <td>Coins mined</td>
      <td>{totalCoin}</td>
    </tr>
    <tr>
      <td>Coins spent</td>
      <td>{totalPrice}</td>
    </tr>
    <tr>
      <td>Available coins </td>
      <td>{totalCoin - totalPrice}</td>
    </tr>
    </tbody>
  </table>
}
